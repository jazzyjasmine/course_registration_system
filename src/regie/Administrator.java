package regie;

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


}
