/* 
 * Test.java
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class KafkaOffset{
    public static void main(String[] args) throws Exception {
    
        String[] arg0 = { "--broker-list",
                "10.182.200.27:9092,10.182.200.28:9092,10.182.200.29:9092",
                "--topic", "live-server-report", "--time", "-1" };
        
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
       // System.out.println(msg);
        Pattern pattern = Pattern.compile("live-server-report:[0-9]{1,2}:([0-9]+)");
        Matcher matcher = pattern
                .matcher(msg);

        while(matcher.find()){
           System.out.println(matcher.group()); 
           res += Long.valueOf(matcher.group(1));
        }
        System.out.println(res);
    }
}
