/* 
 * RedisData.java
 * 
 * Created on 2015年4月1日
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
package redis;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisData implements Serializable{
    private String key;
    private double score;
    private String member;
    private String field;
    private String value ;
    private long incrby ;
    
    public RedisData() {
    
        super();
    }
    
    public RedisData(String key, double score, String member) {
    
        super();
        this.key = key;
        this.score = score;
        this.member = member;
    }
    
    public RedisData(String key, String field, long incrby) {
        
        super();
        this.key = key;
        this.field = field;
        this.incrby = incrby;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getIncrby() {
        return incrby;
    }

    public void setIncrby(long incrby) {
        this.incrby = incrby;
    }
    
}
