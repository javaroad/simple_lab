/* 
 * MatcherTest.java
 * 
 * Created on 2014年9月5日
 * 
 * Copyright(C) 2014, by letv.com.
 * 
 * Original Author: yangxuan
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package regular.express;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest{
    public static void main(String[] args) {
    
        // regex3();
//        System.out
//                .println("/121/24/73/bcloud/140783/ver_00_22-312916394-avc-479830-aac-32002-2608640-169795864-89876fe096e9dfba199a8abe1c6b47d3-1426861471150_mp4/ver_00_22_79_187_2_1010312_96257128.ts"
//                        .matches("[\\S]+_mp4/[\\S]+.ts$"));
//        System.out
//                .println("/121/24/73/bcloud/140783/ver_00_22-312916394-avc-479830-aac-32002-2608640-169795864-89876fe096e9dfba199a8abe1c6b47d3-1426861471150_mp4/ver_00_22_79_187_2_1010312_96257128.ts"
//                        .replaceAll("_mp4/[\\S]+.ts$", ".mp4"));
//        System.out.println("aaaa.log".matches("[\\S]*.log$"));
//        
        regex5();
        // System.out.println("".matches("^[0-9]*$"));
        // System.out.println(Arrays.toString("yx;yx,yaaa aa".split(";|,|\\s")));
        // System.out.println("yx;yx,yaaa aa".split(";|,|\\s").length);
    }
    
    public static void regex1() {
    
        Pattern pattern = Pattern.compile("#@#1\\|.{19}");
        Matcher matcher = pattern
                .matcher("#@#1|2014-09-05 15:17:20#@#2|2014-09-05 15:17:20");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    public static void regex2() {
    
        System.out.println("#@#1|2014-09-05 15:17:20#@#2|2014-09-05 15:17:20"
                .replaceAll("#@#1\\|[-:0-9\\s]{19}", ""));
    }
    
    public static void regex3() {
    
        String regEx = ".+/(.+)$";
        String s = "c:/test.txt";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        boolean rs = mat.find();
        System.out.println(rs);
        for (int i = 1; i <= mat.groupCount(); i++) {
            System.out.println(mat.group(i));
        }
    }
    
    public static void regex4() {
    
        boolean mat = Pattern.matches("[0-9]{12}", "2014092812289");
        System.out.println(mat);
    }
    
    public static void regex5() {
    
//        boolean mat = Pattern.matches("2.20[3,6]", "2.206");
//        System.out.println(mat);
        System.out.println("2.206bb+\\x03".matches("^2.20[3,6,7]b\\d{6}$"));
    }
    
    public static void regex6() {
    
        Pattern p = Pattern.compile("/[^/]+[.][^/]+/");
        Matcher m = p
                .matcher("/140/38/113/wasucn/0/vodpc-ls.wasu.cn/pcsan09/mams/vod/201504/10/07/2015041007001954477e5b84d.mp4");
        if (m.find()) {
            System.out.println(m.end());
            System.out.println(m.group(0));
        }
        System.out
                .println("/140/38/113/wasucn/0/vodpc-ls.wasu.cn/pcsan09/mams/vod/201504/10/07/2015041007001954477e5b84d.mp4"
                        .substring(m.start()));
//        
        Pattern p1 = Pattern.compile("/");
        Matcher m1 = p1
                .matcher("/115/52/103/acloud/117059/201412/6a39e30d-eb9e-441e-a720-0b989eaae96f_0.mp4");
        while (m1.find()) {
            //System.out.println(m1.end());
            System.out.println(m1.start());
        }
        int i = 0 ;
        int  end = 0 ;
        while(m1.find()){
            i++ ;
            if(i == 6){
                end =   m1.start() ;
            }
        }
        System.out.println("/115/52/103/acloud/117059/201412/6a39e30d-eb9e-441e-a720-0b989eaae96f_0.mp4".substring(end));
    }
}
