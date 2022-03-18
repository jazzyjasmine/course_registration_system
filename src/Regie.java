import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Regie {
    private final MongoDBConnect mongodbConnect;
    private final MySQLConnect mysqlConnect;
    private Map<Integer, Person> uid_to_person;
    private ArrayList<Student> students;
    private ArrayList<Instructor> instructors;
    private ArrayList<Administrator> administrators;
    private ArrayList<Course> courses;

    public Regie() {
        mongodbConnect = MongoDBConnect.getInstance();
        mysqlConnect = MySQLConnect.getInstance();
        reset();
    }

    public void reset() {
        try {
            // get all students
            Statement statement = mysqlConnect.dbConnection.createStatement();
            ResultSet resultSetStudent = statement.executeQuery("select people_students.id as id, firstname, lastname, email, division, student_type, major, graduation_date from (select * from people where role = 1) people_students left join student on people_students.id = student.id");
            while (resultSetStudent.next()) {
                Student curr = new Student(
                        resultSetStudent.getString("id"),
                        resultSetStudent.getString("firstname"),
                        resultSetStudent.getString("lastname"),
                        resultSetStudent.getString("email"),
                        resultSetStudent.getString("division"),
                        resultSetStudent.getInt("student_type"),
                        resultSetStudent.getString("major"),
                        resultSetStudent.getString("graduation_date")
                );
                uid_to_person.put(resultSetStudent.getInt("id"), curr);
                students.add(curr);
            }

            // get all instructors
            ResultSet resultSetInstructor = statement.executeQuery("select id, firstname, lastname, email, division from people where role = 2");
            while (resultSetInstructor.next()) {
                Instructor curr = new Instructor(
                        resultSetInstructor.getString("id"),
                        resultSetInstructor.getString("firstname"),
                        resultSetInstructor.getString("lastname"),
                        resultSetInstructor.getString("email"),
                        resultSetInstructor.getString("division")
                );
                uid_to_person.put(resultSetInstructor.getInt("id"), curr);
                instructors.add(curr);
            }

            // get all administrators
            ResultSet resultSetAdministrator = statement.executeQuery("select id, firstname, lastname, email, division from people where role = 4");
            while (resultSetAdministrator.next()) {
                Administrator curr = new Administrator(
                        resultSetAdministrator.getString("id"),
                        resultSetAdministrator.getString("firstname"),
                        resultSetAdministrator.getString("lastname"),
                        resultSetAdministrator.getString("email"),
                        resultSetAdministrator.getString("division")
                );
                uid_to_person.put(resultSetAdministrator.getInt("id"), curr);
                administrators.add(curr);
            }

            // get all courses
            ResultSet resultSetCourse = statement.executeQuery("select * from course");
            while (resultSetCourse.next()) {
                Course curr = new Course(
                        resultSetCourse.getString("course_id"),
                        resultSetCourse.getString("course_name"),
                        resultSetCourse.getString("instructor_id"),
                        resultSetCourse.getInt("capacity"),
                        resultSetCourse.getString("address"),
                        resultSetCourse.getInt("weekday"),
                        resultSetCourse.getString("start_time"),
                        resultSetCourse.getString("end_time"),
                        resultSetCourse.getString("division"),
                        resultSetCourse.getString("year"),
                        resultSetCourse.getInt("quarter"),
                        resultSetCourse.getInt("grade_type"),
                        resultSetCourse.getString("description")
                );
                courses.add(curr);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Person getPerson(int uid, String password) throws Exception {
        Map<Integer, String> account_to_password = mongodbConnect.getAccounts();
        if (!account_to_password.containsKey(uid)) {
            throw new Exception("Uid not exists!");
        }

        if (account_to_password.get(uid) != password) {
            throw new Exception("Wrong password!");
        }

        return uid_to_person.get(uid);
    }

}
