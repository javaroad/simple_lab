/* 
 * SplitTest.java
 * 
 * Created on 2015年1月23日
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

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

public class SplitTest{
    
    private final static String CMD_V = "source /etc/profile; source ~/.bash_profile;jps -v|grep {0}";
    public static void main(String[] args) {
        String aa = "1" +"\t"+ "2"+"\t"+ "3" ;
        
        
        System.out.println(aa.split("\t",1).length);
        System.out.println(StringUtils.splitPreserveAllTokens(aa,"\t",2)[1]);
//        
//        System.out.println(MessageFormat.format(CMD_V,
//                    "calcbandwidth") + "|awk '{print $1}'");
        
        double a = (double) 1000/7;
        System.out.println(a);
        
        System.out.println(new Double(a).longValue());
        System.out.println(StringUtils.substring("abcd", 0,10));
    }
}
