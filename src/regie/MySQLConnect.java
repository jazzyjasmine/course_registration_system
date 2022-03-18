package regie;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnect {
    /* Singleton Pattern */
    private static final MySQLConnect instance = new MySQLConnect();

    public Connection dbConnection;

    private MySQLConnect() {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/regie", "root", "rootps123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MySQLConnect getInstance() {
        return instance;
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

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
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

    public void addPeopleOnly(String id,
                              String firstname,
                              String lastname,
                              String email,
                              String division,
                              String role) {
        try {

            String sql = "INSERT INTO people " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, division);
            preparedStatement.setString(6, role);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudent(String id,
                           String firstname,
                           String lastname,
                           String email,
                           String division,
                           String student_type,
                           String major,
                           String graduation_date) {
        try {
            addPeopleOnly(id, firstname, lastname, email, division, "1");

            String sql = "INSERT INTO student " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, student_type);
            preparedStatement.setString(3, major);
            preparedStatement.setString(4, graduation_date);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentCourseRelation(String student_id,
                                         String course_id) {
        try {

            String sql = "INSERT INTO student_course_relation " +
                    "VALUES (?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, student_id);
            preparedStatement.setString(2, course_id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropStudentCourseRelation(String student_id,
                                          String course_id) {
        try {

            String sql = "DELETE FROM student_course_relation " +
                    "WHERE student_id = ? AND course_id = ?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, student_id);
            preparedStatement.setString(2, course_id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTACourseRelation(String ta_id,
                                    String course_id) {
        try {
            String sql = "INSERT INTO ta_course_relation " +
                    "VALUES (?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, ta_id);
            preparedStatement.setString(2, course_id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGrade(String student_id,
                         String course_id,
                         char grade) {
        try {
            String sql = "INSERT INTO grade " +
                    "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, course_id);
            preparedStatement.setString(2, student_id);
            preparedStatement.setString(3, String.valueOf(grade));

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropGrade(String student_id,
                         String course_id) {
        try {
            String sql = "DELETE FROM grade WHERE " +
                    "student_id = ? AND course_id = ?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, student_id);
            preparedStatement.setString(2, course_id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getGradesByCourseId(String course_id) throws SQLException {
        Map<String, String> res = new HashMap<>();
        MySQLConnect mysqlConnect = MySQLConnect.getInstance();
        Statement statement = mysqlConnect.dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select student_id, grade from grade where course_id = " + course_id);
        while (resultSet.next()) {
            res.put(resultSet.getString("student_id"), resultSet.getString("grade"));
        }
        return res;
    }

    public ArrayList<String> getCoursesbyTAId(String TA_id) throws SQLException {
        ArrayList<String> course_ids = new ArrayList<>();
        MySQLConnect mysqlConnect = MySQLConnect.getInstance();
        Statement statement = mysqlConnect.dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select course_id from ta_course_relation where ta_id = " + TA_id);
        while (resultSet.next()) {
            course_ids.add(resultSet.getString("course_id"));
        }
        return course_ids;
    }


}
