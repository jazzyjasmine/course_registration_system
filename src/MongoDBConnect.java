import com.mongodb.client.*;
import org.bson.Document;
import java.util.HashMap;
import java.util.Map;

public class MongoDBConnect {
    public MongoCollection<Document> collection;
    public MongoDBConnect() {
        MongoClient client = MongoClients.create("mongodb://root:rootps123@localhost:27017/regie");
        MongoDatabase database = client.getDatabase("regie");
        collection = database.getCollection("account");
    }

    public Map<Integer, String> getAccounts() {
        Map<Integer, String> account_to_password = new HashMap<>();
        try (MongoCursor<Document> cur = collection.find().iterator()) {
            while (cur.hasNext()) {
                Document doc = cur.next();
                account_to_password.put(Integer.parseInt(doc.get("uid").toString()), doc.get("password").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account_to_password;
    }

    public void insertAccount(int uid, String password) {
        try {
            Document document = new Document();
            document.append("uid", Integer.toString(uid));
            document.append("password", password);

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        MongoDBConnect test = new MongoDBConnect();
//        test.insertAccount(4, "administratorfortest");
//        System.out.println(test.getAccounts());
//    }

}
