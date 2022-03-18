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
    }
}
