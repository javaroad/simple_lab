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

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtils {
    private static ConcurrentHashMap<ProgressStatus, ProgressStatus> map =
            new ConcurrentHashMap<ProgressStatus, ProgressStatus>();

    public static void main(String[] args) {
        Double a = new Double("1.25");
        System.out.println(a.doubleValue());
        float m = 1.21f;
        System.out.println(m);
    }

    private static void split1() {

        String str = "08/Oct/2014:17:02:00 +0800\t3956\t\t200";
        String[] arr = StringUtils.splitPreserveAllTokens(str, "\t");
        int i = 3;
        System.out.println(arr[i++]);
    }

    private static void split2() {

        String str = "08/Oct/2014:17:02:00 +0800\t3956\t\t200";
        String[] arr = str.split("\t", 2);
        System.out.println(arr[1]);
    }
}
