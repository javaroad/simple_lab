package zookeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaUtil{
    private static Logger log = LoggerFactory.getLogger(KafkaUtil.class);
    
    public static long getOffSetByTopic(String[] arg0, String topic) throws IOException {
    
        Process pro = Runtime.getRuntime().exec(
                "java -cp E:\\workspace\\wk1\\lab\\target\\lab.jar kafka.tools.GetOffsetShell "
                        + StringUtils.join(arg0, " "));
        BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String msg = "";
        String tmp = null;
        long res = 0;
        while ((tmp = br.readLine()) != null) {
            msg += tmp + "\n";
        }
        Pattern pattern = Pattern.compile(topic + ":[0-9]{1,2}:([0-9]+)");
        Matcher matcher = pattern.matcher(msg);
        
        while (matcher.find()) {
            log.debug("partition offset：" + matcher.group());
            res += Long.valueOf(matcher.group(1));
        }
        return res;
    }
}
