public class Student extends Person {
    public int student_type;
    public String major;
    public String graduation_date;

    public Student(String id, String firstName, String lastName, String email, String division, int student_type, String major, String graduation_date) {
        super(id, firstName, lastName, email, division, 1);
        this.student_type = student_type;
        this.major = major;
        this.graduation_date = graduation_date;
    }



}
