import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnect {
    /* Singleton Pattern */
    private static final MySQLConnect instance = new MySQLConnect();

    public Connection dbConnection;

    public MySQLConnect() {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/regie", "root", "rootps123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MySQLConnect getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "rootps123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("User"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
