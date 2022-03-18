package regie;

public abstract class Person {

    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String division;
    public int role;

    public Person(String id, String firstName, String lastName, String email, String division, int role) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.division = division;
        this.role = role;
    }
}
