package lab;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReflectTest {

    public static Class<?> clazz = ReflectTest.class;
    
    private Integer integerValue;
    private int intValue;
    private List<String> genericTypeValue = new ArrayList<String>();
    private List nullGenericType;
    
    public String getType(){
        TypeVariable<?>[] types = this.getClass().getTypeParameters();
        return types[0].getName();
    }
    public class SubClass extends ReflectTest{
        public String getType(){
            TypeVariable<?>[] types = this.getClass().getTypeParameters();
            return types[0].getName();
        };
    }
   
    
    @Test
    public void testFields() throws SecurityException, NoSuchFieldException{
        Field IntegerField = clazz.getDeclaredField("integerValue");
        Field intField = clazz.getDeclaredField("intValue");
        //Integer类型属性
        Class<?> integerType = IntegerField.getType();
        //当类型可能有类型参数时用此方法,如List<T>
        Type integerGenericType = IntegerField.getGenericType();
        //当类型没有类型参数时，可强制转型为Class<?>,则integerType.equals(integerGenericType2)为true
        Class<?> integerGenericType2 = (Class<?>)IntegerField.getGenericType();//
        
        //类型的名称
        String integerTypeName = IntegerField.getType().getName();
        String integerGenericTypeName = ((Class<?>)IntegerField.getGenericType()).getName();
        System.out.println("integerTypeName:"+integerTypeName);
        System.out.println("integerGenericTypeName:"+integerGenericTypeName);
        
        Class<?> intType = intField.getType();
        Type intGenericType = intField.getGenericType();
        
        //genericType与type是否相等(genericType通过Class<?>转型后才会相等)
        boolean b1 = integerType.equals(integerGenericType); //false
        boolean b2 = integerType.equals(integerGenericType2);//true
        System.out.println("integerType.equals(integerGenericType):"+b1);
        System.out.println("integerType.equals(integerGenericType2):"+b2);
        
        //比较得到的类型是不是某个类型
        boolean c1 = integerType.equals(Integer.class);    //与Integer类型比较
        boolean c2 = intType.equals(Integer.TYPE);        //与int比较
        boolean c3 = intType.equals(intGenericType);
        System.out.println("integerType.equals(Integer.class):"+c1);
        System.out.println("intType.equals(Integer.TYPE):"+c2);
        System.out.println("intType.equals(Integer.TYPE):"+c3);
    }
    
    @Test
    public void testGenericType() throws SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException{
        
        //如果类属性的类型带有类型参数，如List<T>
        //那么想获取类型T时用field.getGenericType();方法,然后转型为参数化类型[ParameterizedType]
        Field genericTypeField1 = clazz.getDeclaredField("genericTypeValue");
        Field genericTypeField2 = clazz.getDeclaredField("nullGenericType");
        
        ParameterizedType genericType1 = (ParameterizedType)genericTypeField1.getGenericType();
//        nullGenericType并没有参数类型，强制转换为(ParameterizedType)会抛异常！
//        只能转换为(Class<?>)或通过getType()获得类型
//        ParameterizedType genericType2 = (ParameterizedType)genericTypeField2.getGenericType();
        Class<?> type1 = genericTypeField1.getType();//type1为List<String>的类型！
        Class<?> Type2 = (Class<?>)genericTypeField2.getGenericType();
        Class<?> Type2_1 = genericTypeField2.getType();
        
        //通过参数化类型[ParameterizedType]获得声明的参数类型的数组
        Type[] types1 = genericType1.getActualTypeArguments();
        Class<?> typeValue1 = (Class<?>) types1[0];
        System.out.println("typeValue1:"+typeValue1);//class test.String
        System.out.println("typeValue2:"+Type2);//interface java.util.List
        System.out.println("typeValue2_1:"+Type2_1); //interface java.util.List
        
        if(typeValue1.equals(String.class))    //true
            System.out.println("typeValue1.equals(String.class)?"+typeValue1.equals(String.class));
        if(Type2.equals(List.class))    //true
            System.out.println("Type2.equals(List.class)?"+Type2.equals(List.class));
        
        //创建包含参数类型的类型的对象[异常！类型声明为接口List，而却要创建ArrayList]
//        ArrayList<String> newInstance = (ArrayList<String>) type1.newInstance();
//        newInstance.add("123");
        
    }
    
}