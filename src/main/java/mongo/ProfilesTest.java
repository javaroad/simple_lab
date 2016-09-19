package mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ProfilesTest{
    private static final Logger LOGGER = LoggerFactory.getLogger("ProfilesTest");
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch().start();
        try {
            List<ServerAddress> addresses = getServerAddresses();
            
            MongoClient client = new MongoClient(addresses);
            MongoDatabase db = client.getDatabase("testdb");
            MongoCollection coll = db.getCollection("table4");
            // 插入
            List<Document> list = new ArrayList<Document>() ;
            for (int i = 0; i < 100000; i++) {
                org.bson.Document object = new org.bson.Document();
                object.append("id", i);
                object.append("a" + i, "testval2" + i);
                object.append("b" + i, "testval" + i);
                list.add(object);
            }
            coll.insertMany(list);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("time:{}", stopwatch.elapsedTime(TimeUnit.SECONDS));
    }
    
    public static List<ServerAddress> getServerAddresses() {
    
        List<ServerAddress> addresses = new ArrayList<ServerAddress>();
        ServerAddress address1 = new ServerAddress("10.140.60.146", 20000);
        ServerAddress address2 = new ServerAddress("10.140.60.148", 20000);
        ServerAddress address3 = new ServerAddress("10.140.60.149", 20000);
        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);
        return addresses;
    }
    
}