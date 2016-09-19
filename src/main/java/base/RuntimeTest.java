/* 
 * RuntimeTest.java
 * 
 * Created on 2014年11月26日
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
package base;

import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.jvm.BufferPoolMetricSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;

public class RuntimeTest{
    public static Object a = new Object();
    public static Object b = new Object();
    
    public static void main(String[] args) {
    
     System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void aaaa() {
    
        Thread t1 = new Thread(){
            @Override
            public void run() {
            
                synchronized (a) {
                    System.out.println("syn a");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    
                    synchronized (b) {
                        System.out.println("syn b");
                    }
                }
            }
        };
        t1.setDaemon(false);
        t1.start();
        
        Thread t2 = new Thread(){
            @Override
            public void run() {
            
                synchronized (b) {
                    System.out.println("syn b");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    
                    synchronized (a) {
                        System.out.println("syn a");
                    }
                }
            }
        };
        t2.setDaemon(false);
        t2.start();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name.substring(0, name.indexOf("@")));
        // ThreadDeadlockDetector deadlock = new ThreadDeadlockDetector();
        // System.out.println(deadlock.getDeadlockedThreads());
        
        ThreadStatesGaugeSet thread = new ThreadStatesGaugeSet();
        
        for (Map.Entry<String, Metric> entry : thread.getMetrics().entrySet()) {
            System.out.println(entry.getKey() + "\t"
                    + ((Gauge) entry.getValue()).getValue());
        }
        System.out
                .println("bufferPool----------------------------------------------");
        BufferPoolMetricSet bufferPool = new BufferPoolMetricSet(
                ManagementFactory.getPlatformMBeanServer());
        for (Map.Entry<String, Metric> entry : bufferPool.getMetrics()
                .entrySet()) {
            System.out.println(entry.getKey() + "\t"
                    + ((Gauge) entry.getValue()).getValue());
        }
        System.out
                .println("garbage----------------------------------------------");
        GarbageCollectorMetricSet garbage = new GarbageCollectorMetricSet();
        for (Map.Entry<String, Metric> entry : garbage.getMetrics().entrySet()) {
            System.out.println(entry.getKey() + "\t"
                    + ((Gauge) entry.getValue()).getValue());
        }
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
                NumberUtils.INTEGER_ONE);
        scheduler.scheduleWithFixedDelay(new JvmPrint(), 5, 5,
                TimeUnit.SECONDS);
    }
    
    public static class JvmPrint implements Runnable{
        @Override
        public void run() {
        
            MemoryUsageGaugeSet memory = new MemoryUsageGaugeSet();
            System.out
                    .println("memory----------------------------------------------");
            for (Map.Entry<String, Metric> entry : memory.getMetrics()
                    .entrySet()) {
                System.out.println(entry.getKey() + "\t"
                        + ((Gauge) entry.getValue()).getValue());
                
            }
        }
    }
    
}
