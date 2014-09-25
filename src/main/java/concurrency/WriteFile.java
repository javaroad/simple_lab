/* 
 * WriteLocalFile.java
 * 
 * Created on 2014年9月25日
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;

public class WriteFile{
    private final static org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(WriteFile.class);
    /**
     * 
     * @method: OutpoutTask write2File
     * @param expireMap
     *        void
     */
    public static void write2File(Map<String, Integer[]> expireMap) {
        if(MapUtils.isEmpty(expireMap)){
            return ;
        }
        DateTime dateTime = new DateTime();
        String currentDateStr = dateTime.toString("yyyyMMddHHmm");
        String path = "d:\\tmp\\file\\" + currentDateStr;
        //先创建目录
        WriteFile.createDir("d:\\tmp\\file\\" );
        // 日期不是同一天
        File file = new File(path);
        OutputStream os = null;
        if (!file.exists()) {
            try {
                LOGGER.info("create new file {}", currentDateStr);
                boolean isNewFile = file.createNewFile();
                if(isNewFile)
                    HiveOutpoutTask.QUEUE.add(path);
            } catch (IOException e) {
                // ignore
                LOGGER.error("LocalOutpoutTask create new file error", e);
            }
        }
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, true));
            for (Entry<String, Integer[]> entry : expireMap.entrySet()) {
                Integer[] intArr = entry.getValue();
                byte[] resultByte = (entry.getKey() + Constants.TAB_SEPERATOR
                        + intArr[0] + Constants.TAB_SEPERATOR + intArr[1] + Constants.NEWLINE)
                        .getBytes();
                os.write(resultByte);
            }
        } catch (IOException e) {
            // ignore
            LOGGER.error("LocalOutpoutTask write file error", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
    
    private static void createDir(String localPathConfig) {
    
        File file = new File(localPathConfig);
        if(!file.exists()){
            file.mkdirs() ;
        }
    }
}
