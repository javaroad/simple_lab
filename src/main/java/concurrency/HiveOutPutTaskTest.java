/* 
 * HiveOutPutTask.java
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

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;

import backtype.storm.utils.RotatingMap;

public class HiveOutPutTaskTest{
    public static void main(String[] args) {
    
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
                NumberUtils.INTEGER_ONE);
        
        final RotatingMap<String, Integer[]> map = new RotatingMap<String, Integer[]>(3);
        scheduler.scheduleWithFixedDelay(new LocalOutpoutTask(map
                ), 10, 10,
                TimeUnit.SECONDS);
        
        ExecutorService fixedService = Executors.newFixedThreadPool(1); 
        fixedService.submit(new HiveOutpoutTask());
        
        new Thread(){
            public void run() {
                while(true){
                    Random random = new Random() ;
                    for (int i = 0; i < 10; i++) {
                        synchronized (map) {
                            map.put(""+random.nextInt(10000000), new Integer[]{i,i});
                        }
                       
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                
            };
        }.start(); 
    }
}
