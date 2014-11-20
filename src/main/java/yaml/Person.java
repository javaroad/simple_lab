/* 
 * Person.java
 * 
 * Created on 2014年9月24日
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
package yaml;

import java.util.Map;

import lombok.Data;

@Data
public class Person{
    private String username = null ;
    private Car car = null ;
    private Map<String,String> map ;
}
