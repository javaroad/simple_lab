package base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class EngineShutdownHook extends Thread {
  
    public void run() {  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");  
          
        Timestamp timestampStop = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampStop) + " - Stopping INFO Engine...");  
        Timestamp timestampStopped = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampStopped) + " - INFO Engine stopped.");  
         
        Timestamp timestampDestroy = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampDestroy) + " - Destroying INFO Engine...");  
          
        Timestamp timestampDestroyed = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampDestroyed) + " - INFO Engine destroyed.");  
          
        Timestamp timestampComplete = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampComplete) + " - Shutdown complete");  
          
        Timestamp timestampHalt = new Timestamp(new java.util.Date().getTime());  
        System.out.println("[INFO] " + simpleDateFormat.format(timestampHalt) + " - Halting JVM");  
    }  
}  