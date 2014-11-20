/* 
 * CollectionUtils.java
 * 
 * Created on 2014年10月10日
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

import org.apache.commons.lang3.StringUtils;

public class CollectionUtils{
    public static void main(String[] args) {
        split2();
    }

    private static void split1() {
    
        String str = "08/Oct/2014:17:02:00 +0800\t3956\t\t200";
        String[] arr = StringUtils.splitPreserveAllTokens(str,"\t");
        int i =3 ;
        System.out.println(arr[i++]);
    }
    private static void split2() {
        
        String str = "08/Oct/2014:17:02:00 +0800\t3956\t\t200";
        String[] arr = str.split("\t",2);
        System.out.println(arr[1]);
    }
}
