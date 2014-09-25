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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest{
    public static void main(String[] args) {
    
        regex3();
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
}
