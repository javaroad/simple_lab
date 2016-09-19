/* 
 * BloomFilterTest.java
 * 
 * Created on 2015年6月18日
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
package base;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest{
    public static void main(String[] args) throws InterruptedException {
    
        BloomFilter filter = BloomFilter.create(Funnels.stringFunnel(),
                10000000);
        for (int i = 0; i < 100000; i++) {
            filter.put("aba" + i);
        }
        
        Thread.sleep(1000000);
        System.out.println(filter.mightContain("aba"));
        
    }
}
