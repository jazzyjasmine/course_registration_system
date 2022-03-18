package regie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class TA extends Student implements Teach {
    public TA(String id, String firstName, String lastName, String email, String division, int student_type, String major, String graduation_date) {
        super(id, firstName, lastName, email, division, student_type, major, graduation_date);
    }

    @Override
    public Map<String, String> viewGrade(String course_id) throws SQLException {
        MySQLConnect connect = MySQLConnect.getInstance();
        return connect.getGradesByCourseId(course_id);
    }

    @Override
    public ArrayList<Map<String, String>> viewCourses() throws SQLException {
        Regie regie = Regie.getInstance();
        MySQLConnect connect = MySQLConnect.getInstance();
        ArrayList<String> course_ids = connect.getCoursesbyTAId(id);
        ArrayList<Map<String, String>> res = new ArrayList<>();
        for (String course_id : course_ids) {
            res.add(regie.cid_to_course.get(course_id).displayCourse());
        }
        return res;
    }
}
