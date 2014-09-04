package lab;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class CharTest  extends Assert {
	@Test
	public void testSplit() {
		String ch = "中";
		printByteLength(ch, "GBK");
		printByteLength(ch, "utf-8");
	}
	
	 public static void printByteLength(String s, String encodingName) {   
	        System.out.print("字节数：");   
	        try {   
	            System.out.print(s.getBytes(encodingName).length);   
	        } catch (UnsupportedEncodingException e) {   
	            e.printStackTrace();   
	        }   
	        System.out.println(";编码：" + encodingName);   
	    } 
	
}
