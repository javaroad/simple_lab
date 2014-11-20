/* 
 * StopWatchTest.java
 * 
 * Created on 2014年9月28日
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

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class StopWatchTest{
    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.createStarted() ;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
