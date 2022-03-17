import java.sql.*;

public class MySQLConnect {
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
