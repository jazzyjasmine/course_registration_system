import java.sql.*;

public class MySQLConnect {
    private Connection dbConnection;
    public MySQLConnect() {
        try {
            this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "rootps123");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
