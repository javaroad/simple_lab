/* 
 * CuratorTest.java
 * 
 * Created on 2014年11月19日
 * 
 * Copyright(C) 2014, by 360buy.com.
 * 
 * Original Author: yangxuan
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package zookeeper;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class CuratorTest{
    public static void main(String[] args) {
    
        // CuratorFramework client = CuratorFrameworkFactory.builder()
        // .connectString("cdn39:2181").namespace("live-calc")
        // .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
        // .connectionTimeoutMs(5000).build();
        //
        //
        // try {
        // client.start();
        // client.checkExists().forPath("/live-calc/partition_7");
        // client.getData().watched().inBackground().forPath("/live-calc/partition_7");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        
       testzk1();
        
    }
    
    private static void testzk1() {
        
        try {
            ZooKeeper zk = new ZooKeeper("cdn39:2181,cdn38:2181,cdn37:2181", 500000, new Watcher(){
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                
                    // dosomething
                }
            });
            List<String>  list = zk.getChildren("/live-calc/live-calc",true);
            long res =  0 ;
            System.out.println(list);
            for (String ss : list) {
                byte[] bytes = zk.getData("/live-calc/live-calc/"+ss, true,
                        null);
                String str = new String(bytes);
                String offset = str.substring(str.indexOf("\"offset\":"),
                        str.indexOf(",\"partition\"")).replace("\"offset\":", "");
                res += Long.valueOf(offset);
                System.out.println(ss+":"+offset);
            }
           
            
            System.out.println(res);
        } catch (Exception e) {
        }
        
    }
    

    private static void testzk2() {
    
        try {
            ZooKeeper zk = new ZooKeeper("10.182.200.27:2181,10.182.200.28:2181,10.182.200.29:2181", 500000, new Watcher(){
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                
                }
            });
            List<String>  list = zk.getChildren("/live-calc/live-calc",true);
            long res =  0 ;
            System.out.println(list);
            for (String ss : list) {
                byte[] bytes = zk.getData("/live-calc/live-calc/"+ss, true,
                        null);
                String str = new String(bytes);
                String offset = str.substring(str.indexOf("\"offset\":"),
                        str.indexOf(",\"partition\"")).replace("\"offset\":", "");
                res += Long.valueOf(offset);
                System.out.println(ss+":"+offset);
            }
           
            
            System.out.println(res);
        } catch (Exception e) {
        }
        
    }
}
