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

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class CuratorTest{
    public static final String ZK_HOSTS= "cdn36:2181,cdn37:2181,cdn39:2181" ;
    
    public static void main(String[] args)throws Exception {
        testzk1() ;
        
    }
    
    private static void testzk3() throws Exception {
        ZooKeeperOperator zk = new ZooKeeperOperator();
        zk.connect("cdn39,cdn37");
       /* ZooKeeper zk = new ZooKeeper(ZK_HOSTS,
                10000, new Watcher(){
                    // 监控所有被触发的事件
                    public void process(WatchedEvent event) {
                        System.out.println(event.getState()  ==  KeeperState.SyncConnected);
                        System.out.println(event.getPath() + "\t"
                                + event.getType());
                    }
                });*/
//        Stat stat = zk.exists("/cdn-log-test", true);
//        if(stat==null){
//            zk.create("/zktest", "ttttt1".getBytes(), Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT);
//        }
        Stat stat = new Stat();
        byte[] bytes = zk.getData("/storm/errors/calcbandwidth-96-1422269276/kafkaspout/e0000000010");
        System.out.println(new String(bytes,"UTF-8"));
//        zk.exists("/root/childone", true);
//        zk.create("/zktest/childone", "childone".getBytes(), Ids.OPEN_ACL_UNSAFE,
//
//                CreateMode.EPHEMERAL);
//        Thread.sleep(10000);
    }
    
    
    private static void testzk1() {
    
        try {
//            ZooKeeper zk = new ZooKeeper("cdn39:2181,cdn38:2181,cdn37:2181",
//                    500000, new Watcher(){
//                        // 监控所有被触发的事件
//                        public void process(WatchedEvent event) {
//                        
//                            // dosomething
//                        }
//                    });
            ZooKeeper zk = new ZooKeeper(ZK_HOSTS,
                    500000, new Watcher(){
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                    
                    // dosomething
                }
            });
           // List<String> list = zk.getChildren("/top-url-beta/top-url-beta", true);
             List<String> list = zk.getChildren("/top-url-beta/top-url-beta", true);
            long res = 0;
            System.out.println(list);
            for (String ss : list) {
                byte[] bytes = zk.getData("/top-url-beta/top-url-beta/" + ss, true,
                        null);
                String str = new String(bytes);
                String offset = str.substring(str.indexOf("\"offset\":"),
                        str.indexOf(",\"partition\"")).replace("\"offset\":",
                        "");
                res += Long.valueOf(offset);
                System.out.println(ss + ":" + offset);
            }
            zk.close(); 
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private static void testzk2() {
    
        CuratorFramework client = createSimple(ZK_HOSTS);
        
        try {
            client.start();
            List<String> list = watchedGetChildren(client,
                    "/cdn-calc-test/cdn-calc-test");
            long res = 0;
            System.out.println(list);
            for (String ss : list) {
                String str = watchedGetData(client, "/cdn-calc-test/cdn-calc-test/"
                        + ss);
                
                String offset = str.substring(str.indexOf("\"offset\":"),
                        str.indexOf(",\"partition\"")).replace("\"offset\":",
                        "");
                res += Long.valueOf(offset);
                System.out.println(ss + ":" + offset);
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static CuratorFramework createSimple(String connectionString) {
    
        // these are reasonable arguments for the ExponentialBackoffRetry. The
        // first
        // retry will wait 1 second - the second will wait up to 2 seconds - the
        // third will wait up to 4 seconds.
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,
                3);
        
        // The simplest way to get a CuratorFramework instance. This will use
        // default values.
        // The only required arguments are the connection string and the retry
        // policy
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }
    
    public static List<String> watchedGetChildren(CuratorFramework client,
            String path) throws Exception {
    
        /**
         * Get children and set a watcher on the node. The watcher notification
         * will come through the CuratorListener (see setDataAsync() above).
         */
        return client.getChildren().watched().forPath(path);
    }
    
    public static String watchedGetData(CuratorFramework client, String path)
            throws Exception {
    
        /**
         * Get children and set a watcher on the node. The watcher notification
         * will come through the CuratorListener (see setDataAsync() above).
         */
        return new String(client.getData().watched().forPath(path));
    }
    
}
