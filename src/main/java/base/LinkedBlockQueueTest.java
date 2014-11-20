/* 
 * LinkedBlockQueueTeset.java
 * 
 * Created on 2014年9月26日
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

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockQueueTest{
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>() ;
       String str = queue.poll(1,TimeUnit.SECONDS);
        System.out.println(str);
        queue.put("1");
        queue.put("2");
        System.out.println(queue.contains("1"));
        System.out.println(queue.size());
        System.out.println(queue.poll(1,TimeUnit.SECONDS));
        System.out.println(queue.poll(1,TimeUnit.SECONDS));
        System.out.println(queue.size());
        System.out.println(queue.poll());
    }
}
