package unittest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import regie.*;

public class StudentTest {
    Regie regie;

    @BeforeEach
    void setUp() {
        regie = Regie.getInstance();
    }

    @Test
    @DisplayName("register course with invalid course id")
    void registerCourseInvalidCourseId() {
        Student d = (Student) regie.uid_to_person.get(7);

        Exception exception = assertThrows(Exception.class, () -> {
            d.registerCourse("20");
        });

        String expectedMessage = "Invalid course id!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("register course beyond course max capacity")
    void registerCourseBeyondCourseCapacity() {
        Student d = (Student) regie.uid_to_person.get(7);

        Exception exception = assertThrows(Exception.class, () -> {
            d.registerCourse("2");
        });

        String expectedMessage = "Course maximum capacity reached!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("register course beyond student's course limit")
    void registerCourseBeyondLimit() throws Exception {
        Student b = (Student) regie.uid_to_person.get(5);
        b.dropCourse("4");
        b.registerCourse("4");

        Exception exception = assertThrows(Exception.class, () -> {
            b.registerCourse("3");
        });

        String expectedMessage = "Already reached course limit!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("drop course with invalid course id")
    void dropCourseInvalidCourseId() {
        Student b = (Student) regie.uid_to_person.get(5);

        Exception exception = assertThrows(Exception.class, () -> {
            b.registerCourse("s");
        });

        String expectedMessage = "Invalid course id!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
