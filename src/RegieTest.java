import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegieTest {
    Regie regie;

    @BeforeEach
    void setUp() {
        regie = Regie.getInstance();
    }

    @Test
    @DisplayName("get courses from database")
    void testResetCourse() {
        assertEquals(4, regie.cid_to_course.size());
        assertEquals("Ruby Programming", regie.cid_to_course.get("3").course_name);
        assertEquals("Gravity", regie.cid_to_course.get("4").course_name);
        assertEquals("Introduction to Physics", regie.courses.get(0).course_name);
        assertEquals(2, regie.cid_to_course.get("2").registered_num);
        assertEquals(3, regie.cid_to_course.get("1").registered_num);
        assertEquals(1, regie.cid_to_course.get("3").registered_num);
        assertEquals(1, regie.cid_to_course.get("4").registered_num);
    }

    @Test
    @DisplayName("get students from database")
    void testResetStudent() {
        assertEquals(3, regie.students.size());
        assertEquals("LA", regie.students.get(0).lastName);
        Student s = (Student) regie.uid_to_person.get(6);
        assertEquals(2, s.course_limit);
    }

    @Test
    @DisplayName("get instructors from database")
    void testResetInstructor() {
        assertEquals(2, regie.instructors.size());
        assertEquals("Witch", regie.instructors.get(1).lastName);
        assertEquals("Iron", regie.instructors.get(0).firstName);
        assertEquals("scarlet@regiemail.com", regie.instructors.get(1).email);
    }

    @Test
    @DisplayName("get tas from database")
    void testResetTA() {
        assertEquals(1, regie.tas.size());
        assertEquals("LD", regie.tas.get(0).lastName);
        TA t = (TA) regie.uid_to_person.get(7);
        assertEquals(3, t.course_limit);
    }

    @Test
    @DisplayName("get person by invalid uid")
    void testGetPersonInvalidUid() {
        Exception exception = assertThrows(Exception.class, () -> {
            regie.getPerson(10, "some_password");
        });

        String expectedMessage = "Uid not exists!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("get person by invalid password")
    void testGetPersonInvalidPassword() {
        Exception exception = assertThrows(Exception.class, () -> {
            regie.getPerson(3, "some_password");
        });

        String expectedMessage = "Wrong password!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("get person properly")
    void testGetPersonProperly() throws Exception {
        Administrator rt = (Administrator) regie.getPerson(1, "rootpassword");
        assertEquals(rt, regie.administrators.get(0));
        Instructor scarlet = (Instructor) regie.getPerson(3, "scarletpassword");
        assertEquals(scarlet, regie.uid_to_person.get(3));
    }




}
