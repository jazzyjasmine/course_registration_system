import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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
    }

    @Test
    @DisplayName("get students from database")
    void testResetStudent() {
        assertEquals(3, regie.students.size());
        assertEquals("LA", regie.students.get(0).lastName);
    }
}
