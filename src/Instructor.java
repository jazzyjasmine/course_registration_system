public class Instructor extends Person implements Teach {
    public Instructor(String id, String firstName, String lastName, String email, String division) {
        super(id, firstName, lastName, email, division, 2);
    }
}
