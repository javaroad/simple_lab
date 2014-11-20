/* 
 * FileTeset.java
 * 
 * Created on 2014年11月18日
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
package file;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class FileTest{
    public static void main(String[] args) {
    
        String a = "aaaaaaaaaaaa\tbbbbbbbbbbbbb\tccccccccccc\tddd\teee\tfff";
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            list.add(a);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
       // for (int i = 0; i < 10; i++) {
//            new Thread(){
//                public void run() {
//                
//                    print("e:\\tmp\\filetest\\test", list);
//                }
//            }.start();
      //  }
        write2File1("e:\\tmp\\filetest\\test", list);
    }
    
    public static void write2File(String path, List<String> list) {
    
        // 日期不是同一天
        File file = new File(path);
        PrintWriter pw = null;
        if (!file.exists()) {
            try {
                boolean isNewFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (list.size() > 0) {
                pw = new PrintWriter(
                        new FileOutputStream(file, true));
                for (String livelog : list) {
                    pw.println(livelog);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(pw);
        }
    }
    
    public static void write2File1(String path, List<String> list) {
        
        // 日期不是同一天
        File file = new File(path);
        OutputStream os = null;
        if (!file.exists()) {
            try {
                boolean isNewFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (list.size() > 0) {
                os = new BufferedOutputStream(new FileOutputStream(file, true));
                for (String livelog : list) {
                    os.write((livelog+"\n").getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
}
