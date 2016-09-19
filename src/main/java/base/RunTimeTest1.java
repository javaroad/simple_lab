package base;

public class RunTimeTest1{
    /**
     * @param args
     */
    public static void main(String[] args) {
    
        Thread thread1 = new Thread(){
            public void run() {
                String[] str = new String[Integer.MAX_VALUE];
                for (int i = 0; i < str.length; i++) {
                    str[i] = "aaaa"+ i ;
                }
            }
        };
        
        Thread thread2 = new Thread(){
            public void run() {
            
                System.out.println("thread2...");
            }
        };
        
        Thread shutdownThread = new Thread(){
            public void run() {
            
                System.out.println("shutdownThread...");
            }
        };
        
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        
        thread1.start();
        thread2.start();
    }
}