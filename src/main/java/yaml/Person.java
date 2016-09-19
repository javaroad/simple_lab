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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
}
