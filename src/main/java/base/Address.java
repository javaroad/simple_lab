package base;  
  
public class Address implements Cloneable{  
      
    private String state;  
    private String province;  
    private String city;  
    
    public Address(){
        System.out.println("super0");
    }
    public Address(String str){
        System.out.println("super1");
    }
    
    public String getState() {  
        return state;  
    }  
    public void setState(String state) {  
        this.state = state;  
    }  
    public String getProvince() {  
        return province;  
    }  
    public void setProvince(String province) {  
        this.province = province;  
    }  
    public String getCity() {  
        return city;  
    }  
    public void setCity(String city) {  
        this.city = city;  
    }  
    @Override  
    public String toString() {  
        StringBuilder sb = new StringBuilder();  
        sb.append("国家：" + state + ", ");  
        sb.append("省：" + province + ", ");  
        sb.append("市：" + city);  
        return sb.toString();  
    }  
    public Address(String state, String province, String city) {  
        super();  
        this.state = state;  
        this.province = province;  
        this.city = city;  
    }  
      
    @Override  
    public Address clone() {  
        Address address = null;  
        try {  
            address = (Address) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return address;  
    }  
}  