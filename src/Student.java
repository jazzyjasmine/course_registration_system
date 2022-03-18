import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Student extends Person {
    public int student_type;
    public String major;
    public String graduation_date;
    public int course_limit;

    public Student(String id, String firstName, String lastName, String email, String division, int student_type, String major, String graduation_date) {
        super(id, firstName, lastName, email, division, 1);
        this.student_type = student_type;
        this.major = major;
        this.graduation_date = graduation_date;
        this.course_limit = student_type == 1 ? 3 : 2;
    }

    public ArrayList<Course> getRegisteredCourses() {
        Regie regie = Regie.getInstance();
        ArrayList<Course> res = new ArrayList<>();
        try {
            Statement statement = MySQLConnect.getInstance().dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select course_id from student_course_relation where student_id = " + id);

            while (resultSet.next()) {
                String cur_course_id = resultSet.getString("course_id");
                res.add(regie.cid_to_course.get(cur_course_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void registerCourse(String course_id) throws Exception {
        Regie regie = Regie.getInstance();
        ArrayList<Course> registered = getRegisteredCourses();
        if (!regie.cid_to_course.containsKey(course_id)) {
            throw new Exception("Invalid course id!");
        }

        if (registered.size() == course_limit) {
            throw new Exception("Already reached course limit!");
        }

        Course targetCourse = regie.cid_to_course.get(course_id);
        if (targetCourse.registered_num == targetCourse.capacity) {
            throw new Exception("Course maximum capacity reached!");
        }

        MySQLConnect connect = MySQLConnect.getInstance();
        connect.addStudentCourseRelation(id, course_id);

        targetCourse.registered_num += 1;

        System.out.println("Registered successfully!");
    }

    public void dropCourse(String course_id) throws Exception {
        Regie regie = Regie.getInstance();
        if (!regie.cid_to_course.containsKey(course_id)) {
            throw new Exception("Invalid course id!");
        }
    }

}
