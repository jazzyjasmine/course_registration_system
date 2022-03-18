package regie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface Teach {
    Map<String, String> viewGrade(String course_id) throws SQLException;
    ArrayList<Map<String, String>> viewCourses() throws SQLException;
}
