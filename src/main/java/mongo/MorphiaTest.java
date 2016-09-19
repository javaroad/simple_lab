/* 
 * MorphiaTest.java
 * 
 * Created on 2015年6月3日
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
package mongo;

import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;


public class MorphiaTest{
    static final  MongoClient client = new MongoClient(TestMongoDB.getServerAddresses());
    public static void main(String[] args) {
    
       
        Morphia morphia = new Morphia();
        morphia.map(Hotel.class).map(Address.class);
        Datastore ds = morphia.createDatastore(client, "morphia");
        Hotel hotel = getHotel();
        Key key = ds.save(hotel);
        System.out.println("save hotel :" + key);
        
        List<Hotel> fourStarHotels = ds.find(Hotel.class).field("stars")
                .greaterThanOrEq(4).asList();
        for (Hotel h : fourStarHotels) {
            System.out.println(h);
        }
    }

    public static Hotel getHotel() {
    
        Address address = new Address() ;
        address.setCity("bj");
        address.setCountry("china");
        address.setStreet("长安街");
        Hotel hotel = new Hotel() ;
        hotel.setAddress(address);
        hotel.setStars(5);
        hotel.setName("北京饭店");
        return hotel ;
    }
}
