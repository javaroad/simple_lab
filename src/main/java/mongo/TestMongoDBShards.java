package mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMongoDBShards {
 
       public static void main(String[] args) {
           
           List<ServerAddress> addresses = getServerAddresses();
           try {
                 MongoClient client = new MongoClient(addresses);
                 MongoDatabase db = client.getDatabase( "testdb");
                 MongoCollection coll = db.getCollection("table1");

                  // 插入
//                 org.bson.Document object = new org.bson.Document();
//                 object.append( "test2", "testval2" );
//                 object.append( "test3", "testval3" );
//
//                 coll.insertOne(object);
//                 ReadPreference preference = ReadPreference. secondary(); 
                 
                 Document dc = new Document() ;
                 dc.append("id", 1);
                 FindIterable dbCursor = coll.find(dc);
                 MongoCursor cursor = dbCursor.iterator() ;
                  while (cursor.hasNext()) {
                       Object dbObject = cursor.next();
                       System. out.println(dbObject.toString());
                 }

          } catch (Exception e) {
                 e.printStackTrace();
          }

   }
       
       public static List<ServerAddress> getServerAddresses() {
           
           List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("10.140.60.146" , 20000);
            ServerAddress address2 = new ServerAddress("10.140.60.148" , 20000);
            ServerAddress address3 = new ServerAddress("10.140.60.149" , 20000);
            addresses.add(address1);
            addresses.add(address2);
            addresses.add(address3);
           return addresses;
       }
}