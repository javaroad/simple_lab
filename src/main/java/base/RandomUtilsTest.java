/* 
 * RandomUtilsTest.java
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
package base;

import org.apache.commons.lang.math.RandomUtils;

public class RandomUtilsTest{
    public static void main(String[] args) {
    
        for (int i = 0; i < 100; i++) {
            
            System.out.println(String.format("%03d", RandomUtils.nextInt(1000)));
        }
        
    }
}
