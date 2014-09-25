/* 
 * YamlTest.java
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;


public class YamlTest{
    public static void main(String[] args) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream in = new FileInputStream("E:\\workspace\\wk1\\lab\\src\\main\\java\\yaml\\test.yaml");
        Person  person = (Person)yaml.loadAs(in,Person.class);
        System.out.println(person);
    }
    
}
