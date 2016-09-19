package jdk;  
  
public interface EightInterface {  
    //jdk1.8中，接口可以实现static的方法，并且调用的时候是用:接口名.方法名();  
    public static String hello(String name) {  
        return "hello "+name;  
    }  
      
    //jdk1.8中，接口可以实现default的方法，调用的时候，必须用该接口的实现类的对象来调用  
    public default void add(int a,int b) {  
        System.out.println("a + b ="+(a+b));  
    }  
}  