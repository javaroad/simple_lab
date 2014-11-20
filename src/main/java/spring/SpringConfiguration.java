/* 
 * SpringConfiguration.java
 * 
 * Created on 2014年11月6日
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
package spring;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;



@Configuration
@PropertySource("classpath:jdbc.properties")

public class SpringConfiguration{
    @Inject
    Environment env;
    @Bean
    public javax.sql.DataSource dataSource() {

      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
      dataSource.setUrl(env.getProperty("jdbc.url"));
      dataSource.setUsername(env.getProperty("jdbc.username"));
      dataSource.setPassword(env.getProperty("jdbc.password"));
      dataSource.setTestOnBorrow(true);
      dataSource.setValidationQuery("SELECT 1");
      return dataSource;
    }
    
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext  ctx = new AnnotationConfigApplicationContext (SpringConfiguration.class);
       // ctx.setEnvironment(new StandardEnvironment());
        BasicDataSource ds = (BasicDataSource)ctx.getBean(BasicDataSource.class);
       System.out.println(ds.getDriverClassName()); 
    }
}
