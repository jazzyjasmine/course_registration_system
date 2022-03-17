import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;

import java.util.ArrayList;

public class MongoDBConnect {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://root:rootps123@localhost:27017/mycustomers");

        MongoDatabase database = client.getDatabase("mycustomers");

        MongoCollection<Document> collection = database.getCollection("customer");

        try (MongoCursor<Document> cur = collection.find().iterator()) {

            while (cur.hasNext()) {

                var doc = cur.next();
                System.out.println(doc);
                System.out.println(doc.keySet());
                var cars = new ArrayList<>(doc.values());

                System.out.printf("%s: %s%n", cars.get(1), cars.get(2));
            }
        }

//        database.createCollection("customer_tester1s");

//        for (String collectionName : database.listCollectionNames()) {
//            System.out.println(collectionName);
//        }

        // Prints out the document.

    }
}
