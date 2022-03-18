import java.util.Map;
import java.sql.*;

public class Administrator extends Person {
    private final MongoDBConnect mongodbConnect;

    private Administrator() {
        super();
        mongodbConnect = MongoDBConnect.getInstance();
    }

    public void insertAccount(int uid, String password) {
        mongodbConnect.insertAccount(uid, password);
    }

    public void addCourse(String course_id,
                          String course_name,
                          String instructor_id,
                          String capacity,
                          String address,
                          String weekday,
                          String start_time,
                          String end_time,
                          String division,
                          String year,
                          String quarter,
                          String grade_type,
                          String description) {
        try {

            String sql = "INSERT INTO course " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = mysqlConnect.dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, course_id);
            preparedStatement.setString(2, course_name);
            preparedStatement.setString(3, instructor_id);
            preparedStatement.setString(4, capacity);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, weekday);
            preparedStatement.setString(7, start_time);
            preparedStatement.setString(8, end_time);
            preparedStatement.setString(9, division);
            preparedStatement.setString(10, year);
            preparedStatement.setString(11, quarter);
            preparedStatement.setString(12, grade_type);
            preparedStatement.setString(13, description);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNonStudentPerson() {

    }


}
