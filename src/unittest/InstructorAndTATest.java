package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import regie.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class InstructorAndTATest {
    Regie regie;

    @BeforeEach
    void setUp() {
        regie = Regie.getInstance();
    }

    @Test
    @DisplayName("view courses for TA")
    void testViewCoursesTA() throws SQLException {
        TA ta = (TA) regie.uid_to_person.get(7);
        ArrayList<Map<String, String>> course_infos = ta.viewCourses();
        assertEquals(1, course_infos.size());
        assertEquals("3", course_infos.get(0).get("course_id"));
    }

    @Test
    @DisplayName("view courses for instructor")
    void testViewCoursesInstructor() throws SQLException {
        Instructor scarlet = (Instructor) regie.uid_to_person.get(3);
        ArrayList<Map<String, String>> course_infos = scarlet.viewCourses();
        Set<String> course_id_set = new HashSet<>();
        for (Map<String, String> course_info : course_infos) {
            course_id_set.add(course_info.get("course_id"));
        }
        Set<String> expected = new HashSet<>();
        expected.add("2");
        expected.add("3");
        assertEquals(expected, course_id_set);
    }

    @Test
    @DisplayName("grade manipulation")
    void testGradeManipulation() throws SQLException {
        Instructor iron = (Instructor) regie.uid_to_person.get(2);
        iron.addGrade("4", "1", 'P');
        Map<String, String> id_to_grade = iron.viewGrade("1");
        assertEquals("P", id_to_grade.get("4"));

        iron.dropGrade("4", "1");
        Map<String, String> id_to_grade_after = iron.viewGrade("1");
        assertEquals(0, id_to_grade_after.size());
    }

}
