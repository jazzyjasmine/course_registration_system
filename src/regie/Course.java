package regie;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Course {
    public String course_id;
    public String course_name;
    public String instructor_id;
    public int capacity;
    public String address;
    public int weekday;
    public String start_time;
    public String end_time;
    public String division;
    public String year;
    public int quarter;
    public int grade_type;
    public String description;
    public int registered_num;

    public Course(String course_id,
                  String course_name,
                  String instructor_id,
                  int capacity,
                  String address,
                  int weekday,
                  String start_time,
                  String end_time,
                  String division,
                  String year,
                  int quarter,
                  int grade_type,
                  String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.instructor_id = instructor_id;
        this.capacity = capacity;
        this.address = address;
        this.weekday = weekday;
        this.start_time = start_time;
        this.end_time = end_time;
        this.division = division;
        this.year = year;
        this.quarter = quarter;
        this.grade_type = grade_type;
        this.description = description;
        getRegisteredNum();
    }

    public void getRegisteredNum() {
        try {
            registered_num = 0;

            Statement statement = MySQLConnect.getInstance().dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(student_id) as num from student_course_relation where course_id = " + course_id);

            while (resultSet.next()) {
                registered_num = resultSet.getInt("num");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> displayCourse() {
        Map<String, String> res = new HashMap<>();
        Regie regie = Regie.getInstance();
        res.put("course_id", course_id);
        res.put("course_name", course_name);
        Person instructor = regie.uid_to_person.get(Integer.parseInt(instructor_id));
        res.put("instructor", instructor.firstName + " " + instructor.lastName);
        res.put("registered count", Integer.toString(registered_num));
        res.put("capacity", Integer.toString(capacity));
        res.put("address", address);
        String weekdayString = switch (weekday) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "";
        };
        res.put("weekday", weekdayString);
        res.put("time", start_time + "~" + end_time);
        res.put("division", division);
        String quarterString = switch (quarter) {
            case 1 -> "Winter";
            case 2 -> "Spring";
            case 3 -> "Summer";
            case 4 -> "Fall";
            default -> "";
        };
        res.put("quarter", year + " " + quarterString);
        String gradetypeString = "";
        if (grade_type == 1) {
            gradetypeString = "P/F";
        } else {
            gradetypeString = "A/B/C/D/F";
        }
        res.put("grade type", gradetypeString);
        res.put("description", description);
        return res;
    }
}
