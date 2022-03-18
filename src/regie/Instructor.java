package regie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Instructor extends Person implements Teach {
    public Instructor(String id, String firstName, String lastName, String email, String division) {
        super(id, firstName, lastName, email, division, 2);
    }

    @Override
    public Map<String, String> viewGrade(String course_id) throws SQLException {
        MySQLConnect connect = MySQLConnect.getInstance();
        return connect.getGradesByCourseId(course_id);
    }

    @Override
    public ArrayList<Map<String, String>> viewCourses() {
        Regie regie = Regie.getInstance();
        ArrayList<Map<String, String>> res = new ArrayList<>();
        for (Course c : regie.courses) {
            if (Objects.equals(c.instructor_id, id)) {
                res.add(c.displayCourse());
            }
        }
        return res;
    }

    public void addGrade(String student_id, String course_id, char grade) {
        MySQLConnect connect = MySQLConnect.getInstance();
        connect.addGrade(student_id, course_id, grade);
    }

    public void dropGrade(String student_id, String course_id) {
        MySQLConnect connect = MySQLConnect.getInstance();
        connect.dropGrade(student_id, course_id);
    }
}
