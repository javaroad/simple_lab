package zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;

public class ZKTest{
    static final String hosts =  "10.182.200.37,10.182.200.36,10.182.200.39" ;
    public static void main(String[] args) throws IOException {
    
        String path = "/cdn-calc-beta/cdn-calc-beta";
        String[] arg0 = { "--broker-list",
                "10.140.60.124:9092,10.140.60.125:9092,10.140.60.126:9092",
                "--topic", "cdn-log-beta", "--time", "-1" };
        try {
            long lastCount = 0;
            
            while (true) {
               // long kafkaoffset = KafkaUtil.getOffSetByTopic(arg0, "cdn-log-0123");
                long counttmp = getOffSet( path);
                if (counttmp == 0) {
                    break;
                }
                System.out.println(counttmp);
              //  System.out.println("kafkaoffset:"+kafkaoffset);
              //  System.out.println(kafkaoffset - counttmp);
                System.out.println("count: " + (counttmp-lastCount) +"  速率：" + ((counttmp-lastCount)
                        / 10) + "条/s");
                lastCount = counttmp;
                
                Thread.sleep(1000 * 10);
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static final ZooKeeperOperator zk = new ZooKeeperOperator();
    static {
        try {
            zk.connect(hosts);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
    }
    public static long getOffSet( String path) throws InterruptedException {
    
        long offset = 0L;
        try {
//            ZooKeeperOperator zk = new ZooKeeperOperator();
//            zk.connect(hosts);
            List<String> cList = zk.getChild(path);
            for (String partition : cList) {
                String tmp = new String(zk.getData(path + "/" + partition));
                offset = offset
                        + Long.valueOf((tmp.substring((tmp.indexOf("\"offset\":") + 9), tmp.indexOf(",\"partition\""))));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block;
            e.printStackTrace();
        } finally {
            //zk.close();
        }
        return offset;
        
    }
}
