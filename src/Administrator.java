import java.util.Map;
import java.sql.*;

public class Administrator extends Person {
    private final MongoDBConnect mongodbConnect;
    public final MySQLConnect mysqlConnect;

    public Administrator(String id, String firstName, String lastName, String email, String division) {
        super(id, firstName, lastName, email, division, 4);
        mongodbConnect = MongoDBConnect.getInstance();
        mysqlConnect = MySQLConnect.getInstance();
    }

    public void insertAccount(int uid, String password) {
        mongodbConnect.insertAccount(uid, password);
    }



//    public static void main(String[] args) {
//        Administrator test = new Administrator();
//        test.addPeopleOnly("10", "Jasmine", "Ma", "zh@gmail.com", "PSD", "4");
//    }


}
