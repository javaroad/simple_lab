/* 
 * WandoujiaTest.java
 * 
 * Created on 2014年9月11日
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
package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WandoujiaTest{
    
    public static final String WDJ_ID = "1009581a";
    public static final String WDJ_KEY = "leshi";
    
    public static InputStream callWandoujia() throws ClientProtocolException,
            IOException {
    
        HttpClient httpClient = new DefaultHttpClient();
        long currentTimestamp = new Date().getTime();
        String token = WandoujiaTest.MD5(WandoujiaTest.WDJ_ID
                + WandoujiaTest.WDJ_KEY + currentTimestamp);
        String url = "http://api.wandoujia.com/v1/allApps.json?id=1009581a&token="
                + token + "&timestamp=" + String.valueOf(currentTimestamp);
        HttpGet getRequest = new HttpGet(url);
        HttpResponse response = httpClient.execute(getRequest);
        org.apache.http.HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
    
    private static String MD5(String source) {
    
        MessageDigest md = WandoujiaTest.createMessageDigest();
        if (null == md)
            return null;
        
        md.update(source.getBytes());
        byte b[] = md.digest();
        
        return WandoujiaTest.byteToString(b);
    }
    
    final static String byteToString(byte[] digest) {
    
        if (null == digest)
            return null;
        
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < digest.length; offset++) {
            i = digest[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
    
    private static MessageDigest createMessageDigest() {
    
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // e.printStackTrace();
        }
        
        return md;
    }
    
    public static void main(String[] args) throws ClientProtocolException,
            IOException {
    
        System.out.println(IOUtils.toString(WandoujiaTest.callWandoujia()));
    }
    
}
