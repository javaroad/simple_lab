/* 
 * Jps.java
 * 
 * Created on 2015年3月5日
 * 
 * Copyright(C) 2015, by 360buy.com.
 * 
 * Original Author: yangxuan
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Jps{
    private static Logger logger = LoggerFactory.getLogger("Jps");
    
    public static void main(String[] args) throws IOException {
    
        Connection con = new Connection("10.181.153.52");
        con.connect();
        boolean isAuth = con.authenticateWithPassword("wangzhenzhen", "wzz123");
        if (isAuth) {
            //Jps.remoteCmd("ps aux|grep Test|grep -v grep |awk '{print $2}'", con);
            Jps.remoteCmd("source /etc/profile; source ~/.bash_profile;jps |grep Test", con);
            Jps.remoteCmd("source /etc/profile; source ~/.bash_profile;jstat -gc 8424 |awk 'NR==2{print}'", con);
        }
    }
    
    private static String remoteCmd(String cmd,Connection con)
            throws IOException {
    
        InputStream stdout = null;
        BufferedReader br = null;
        Session session = null;
        String msg = "";
        try {
            session = con.openSession();
            // 远程压缩、删除原文件
            session.execCommand(cmd);
            stdout = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
           
            String line = null;
            while ((line = br.readLine()) != null) {
                msg += line;
            }
            logger.info("output:"+msg);
            
        } catch (IOException e) {
            logger.error(
                    "log-merge,execute remote command error.cmdxz:{},exception:{}",
                    cmd, ExceptionUtils.getStackTrace(e));
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(stdout);
            if (session != null) {
                session.close();
            }
        }
        return msg ;
    }
}
