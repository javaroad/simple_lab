/* 
 * HiveOutpoutTask.java
 * 
 * Created on 2014年9月23日
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
package concurrency;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//1.导入文件到hive中
public class HiveOutpoutTask implements Runnable{
    private Logger LOGGER = LoggerFactory
            .getLogger(HiveOutpoutTask.class);
    public static final LinkedBlockingQueue<String> QUEUE = new LinkedBlockingQueue<String>();
    
    @Override
    public void run() {
    
        while (true) {
            String localFilePath = null ;
            try {
                localFilePath = QUEUE.take();
            } catch (InterruptedException e) {
                //ignore 
            }
            if(StringUtils.isNotEmpty(localFilePath)){
                LOGGER.info("take path from queue. {} ",localFilePath);
            }
        }
    }
    
}
