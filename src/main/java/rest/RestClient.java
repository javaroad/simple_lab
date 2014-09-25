/* 
 * RestClient.java
 * 
 * Created on 2014年9月9日
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

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient{
    public static void main(String[] args) {
    
        System.out.println(test2());
    }

    private static void test1() {
    
        HttpHeaders map = new HttpHeaders();
        map.add("mac", "123456789");
        map.add("osversion", "xp");
        
        HttpEntity en = new HttpEntity(map);
        ResponseEntity<JSONObject> json = new RestTemplate()
                .exchange("http://localhost/mapi/user/zan?id={0}&zan=cancel",
                HttpMethod.GET,en,
                JSONObject.class,"1")
                        ;
        System.out.println(json.getBody());
    }
    
    public static JSONObject test2() {
    
        try {
            
            HttpClient httpClient = new DefaultHttpClient();
            String url = "http://localhost/mapi/user/zan?id=1&zan=cancel";
            HttpGet getRequest = new HttpGet(url);
            
            getRequest.addHeader("mac", "123456789");
            getRequest.addHeader("osversion", "xp");
            
            getRequest.setHeader(new BasicHeader("Accept", "application/json"));
            HttpResponse response = httpClient.execute(getRequest);
            org.apache.http.HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(entity.getContent(), JSONObject.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to retrieve Spittles", e);
        }
    }
}
