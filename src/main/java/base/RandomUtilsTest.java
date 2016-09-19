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

import java.util.HashMap;
import java.util.Map;

public class RandomUtilsTest{
    public static void main(String[] args) {
    
//        for (int i = 0; i < 100; i++) {
//            
//            System.out.println(String.format("%03d", RandomUtils.nextInt(1000)));
//        }
        Map<Integer,Long> map1 = new HashMap<Integer,Long>() ;
        map1.put(111, 123L);
        Map<Integer,Long> map2 = new HashMap<Integer,Long>() ;
        map2.put(111, 124L);
        System.out.println(map1.equals(map2));
    }
}
