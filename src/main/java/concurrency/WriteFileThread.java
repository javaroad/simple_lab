/* 
 * WriteFileThread.java
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
package concurrency;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;

public class WriteFileThread{
    public static void main(String[] args) {
    
        ExecutorService fixedService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            fixedService.execute(new MyThread());
        }
    }
}

class MyThread implements Runnable{
    public void run() {
    
        OutputStream os = null;
        try {
            File dir = new File("d:\\tmp\\ccc\\");
            dir.mkdirs();
            File file = new File("d:\\tmp\\ccc\\bbb.txt");
            if(!file.exists()){
               boolean needCreateNewFile = file.createNewFile() ;
            }
            os = new BufferedOutputStream(new FileOutputStream(file, true));
            for (int i = 0; i < 1000; i++)
                os.write((i + "\t"+i+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
}
