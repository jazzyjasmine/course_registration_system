public abstract class Person {
    public MySQLConnect mysqlConnect;
    public int id;
    public String firstName;
    public String lastName;

    public Person() {
        mysqlConnect = MySQLConnect.getInstance();
    }
}
