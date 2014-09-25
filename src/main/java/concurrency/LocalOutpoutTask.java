/* 
 * OutpoutTask.java
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

import java.util.Map;

import org.slf4j.LoggerFactory;

import backtype.storm.utils.RotatingMap;
/**
 * 
 * 内存数据输出到本地
 *
 *
 * @version 1.0
 */
public class LocalOutpoutTask implements Runnable{
    private final static org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(LocalOutpoutTask.class);
    
    private RotatingMap<String, Integer[]> rotatingMap;
    //最近的一次文件名
    private volatile String latestFile ;
    
    public LocalOutpoutTask(RotatingMap<String, Integer[]> rotatingMap) {
    
        this.rotatingMap = rotatingMap;
    }
    
    @Override
    public void run() {
    
        Map<String, Integer[]> expireMap = null;
        synchronized (rotatingMap) {
            expireMap = rotatingMap.rotate();
        }
        WriteFile.write2File(expireMap);
        
    }
    
    
    
}
