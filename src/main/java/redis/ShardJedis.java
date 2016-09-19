/* 
 * ShardJedisTest.java
 * 
 * Created on 2015年3月30日
 * 
 * Copyright(C) 2015, by letv.com.
 * 
 * Original Author: yangxuan
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

import com.google.common.base.Stopwatch;

public class ShardJedis{
    private final static org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger("ShardJedis");
    private ShardedJedisPool pool;
    
    public ShardJedis(String ips) {
    
        JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
        String[] iparr = ips.split(",");
        List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>();
        for (String ip : iparr) {
            jdsInfoList.add(new JedisShardInfo(ip,6379));
        }
        pool = new ShardedJedisPool(config, jdsInfoList);
    }
    
    public Long zadd(String key, double score, String member) {
    
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            return jds.zadd(key, score, member);
        } catch (Exception e) {
            return null;
        } finally {
            pool.returnResource(jds);
        }
    }
    
    // 批量写redis
    public void phincr(List<RedisData> list) {
        Stopwatch stopwatch = new Stopwatch().start();
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            ShardedJedisPipeline p = jds.pipelined();
            if (CollectionUtils.isNotEmpty(list)){
                for (RedisData sortSetRedisData : list) {
                    p.hincrBy(sortSetRedisData.getKey(),
                            sortSetRedisData.getField(),
                            sortSetRedisData.getIncrby());
                }
                p.sync();
                LOGGER.info("phincr size:{},time:{}", list.size(),
                        stopwatch.elapsedTime(TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            LOGGER.error("ShardJedis.phincr:", e);
        } finally {
            pool.returnResource(jds);
        }
    }
    
    
    // 批量写redis
    public void pzincrby(List<RedisData> list) {
    
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            ShardedJedisPipeline p = jds.pipelined();
            if (CollectionUtils.isNotEmpty(list)) {
                for (RedisData sortSetRedisData : list) {
                    p.zincrby(sortSetRedisData.getKey(),
                            sortSetRedisData.getScore(),
                            sortSetRedisData.getMember());
                }
            }
            p.sync();
        } catch (Exception e) {
            LOGGER.error("ShardJedis.pzincrby:", e);
        } finally {
            pool.returnResource(jds);
        }
    }
    
    public Double zincrby(String key, double score, String member) {
    
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            return jds.zincrby(key, score, member);
        } catch (Exception e) {
            return null;
        } finally {
            pool.returnResource(jds);
        }
    }
    
    public String get(String key) {
        
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            String value =jds.get(key) ;
            return value;
        } catch (Exception e) {
            LOGGER.error("ShardJedis.get,key : "+key,e);
            return null;
        } finally {
            pool.returnResource(jds);
        }
    }
    
    public  Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        
        ShardedJedis jds = null;
        try {
            jds = pool.getResource();
            return jds.zrevrangeWithScores(key,start,end) ;
        } catch (Exception e) {
            LOGGER.error("ShardJedis.get,key : "+key,e);
            return null;
        } finally {
            pool.returnResource(jds);
        }
    }
    
    public static void main(String[] args) {
    
        ShardJedis jedis = new ShardJedis(
                "10.154.156.107,10.154.156.108");
        List<RedisData> sortset = new ArrayList<RedisData>();
        for (int i = 0; i < 1000; i++) {
                sortset.add(new RedisData("A"+i, i, "A"+i));
        }
        jedis.pzincrby(sortset);
        
        Set<Tuple> set = jedis.zrevrangeWithScores("A",0,-1);
        for (Tuple tuple : set) {
            System.out.println("field:"+tuple.getElement()+",score:"+(long)tuple.getScore());
        }
    }
}
