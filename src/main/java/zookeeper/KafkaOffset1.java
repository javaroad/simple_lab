package zookeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;

public class KafkaOffset1{
    public static void main(String[] args) throws Exception {
    
        String[] arg0 = { "--broker-list",
                "10.140.60.124:9092,10.140.60.125:9092,10.140.60.126:9092",
                "--topic", "client-all-log", "--time", "-1" };
        String[] hosts = { "10.182.200.37", "10.182.200.36", "10.182.200.39" };
        String path = "/kafka-client-beat/kafka-client-beta";
        
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        /*
         * String[] arg0 = { "--broker-list", "10.140.60.124:9092,10.140.60.125:9092,10.140.60.126:9092", "--topic",
         * "perf_test1", "--time", "-1" };
         */
        
        Map<Integer, Long> stormMap = new HashMap<Integer, Long>();
        Process pro = Runtime.getRuntime().exec(
                "java -cp E:\\workspace\\wk1\\lab\\target\\lab.jar kafka.tools.GetOffsetShell "
                        + StringUtils.join(arg0, " "));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                pro.getInputStream()));
        String msg = "";
        String tmp = null;
        long res = 0;
        while ((tmp = br.readLine()) != null) {
            msg +=tmp+"\n";
        }
        Pattern pattern = Pattern.compile("client-all-log:([0-9]{1,2}):([0-9]+)");
        Matcher matcher = pattern.matcher(msg);
        
        while (matcher.find()) {
            map.put(Integer.valueOf(matcher.group(1)), Long.valueOf(matcher.group(2)));
            res += Long.valueOf(matcher.group(2));
        }
        System.out.println(res);
        
        ZooKeeperOperator zk = new ZooKeeperOperator();
        
        long offset = 0L;
        try {
            
            zk.connect(StringUtils.join(hosts, ","));
            List<String> cList = zk.getChild(path);
            for (String partition : cList) {
                String tmp1 = new String(zk.getData(path + "/" + partition));
                Integer myPartion = Integer.valueOf((tmp1.substring(tmp1.indexOf("\"partition\":") + 12,tmp1.indexOf(",\"broker\""))));
                offset = offset
                        + Long.valueOf((tmp1.substring((tmp1.indexOf("\"offset\":") + 9), tmp1.indexOf(",\"partition\""))));
                stormMap.put(myPartion, Long.valueOf((tmp1.substring((tmp1.indexOf("\"offset\":") + 9), tmp1.indexOf(",\"partition\"")))));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeeperException e) {
            // TODO Auto-generated catch block;
            e.printStackTrace();
        } finally {
            zk.close();
        }
        
        
        for (int i = 0; i < map.size(); i++) {
            System.out.println(i + " :" + (map.get(i) - stormMap.get(i)));
        }
    }

    

}
