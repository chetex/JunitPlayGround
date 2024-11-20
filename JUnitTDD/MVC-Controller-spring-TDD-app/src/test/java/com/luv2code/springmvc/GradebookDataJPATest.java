package com.luv2code.springmvc;

import com.luv2code.springmvc.entities.*;
import com.luv2code.springmvc.services.StudentGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class GradebookDataJPATest {

    @Autowired
    private StudentGradeService studentGradeServiceMock;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Delete all data from database
        jdbcTemplate.execute("insert into student values (1, 'Ignacio', 'Garcia', 'ignacio.garcia@gmail.com')");
    }

    @Test
    public void createStudentServiceTest() {
        // Call student service and create new student
        studentGradeServiceMock.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeServiceMock.findCollegeStudentByEmailAddress("ignacio.garcia@gmail.com");

        // Assert created student is the same as the one found by email address
        assert collegeStudentEntity.getEmailAddress().equals("ignacio.garcia@gmail.com");
    }

    @Test
    public void updateStudentServiceTest() {
        // Call student service and create new student
        studentGradeServiceMock.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Then update previous student
        studentGradeServiceMock.updateStudent(1);

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeServiceMock.findCollegeStudentByID(1);

        // Assert updated student is the same as the one found by email address
        assert collegeStudentEntity.getFirstname().equals("Ignacio 2");
    }

    @Test
    public void updateAndDeleteStudentServiceTest() {
        // Call student service and create new student
        studentGradeServiceMock.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Then update previous student
        studentGradeServiceMock.updateStudent(1);

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeServiceMock.findCollegeStudentByID(1);

        // Assert updated student is the same as the one found by email address
        assert collegeStudentEntity.getFirstname().equals("Ignacio 2");

        // Then delete previous student
        Boolean exit = studentGradeServiceMock.deleteStudentWithJPA(1);

        // Assert student is deleted
        assert exit;
    }

    @Test
    public void isStudentNullCheckTest() {
        // Call student grade service method check if student is null sending id = 1 and assert student is not null
        assertTrue(studentGradeServiceMock.isStudentNullCheck(1));

        // Given another student with id = 2 and assert student is NULL (student is not created yet)
        assertFalse(studentGradeServiceMock.isStudentNullCheck(0));
    }

    /**
     * This test delete student service test
     */
    @Test
    public void deleteStudentServiceTest() {
        // Call student service and create new student
        studentGradeServiceMock.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeServiceMock.findCollegeStudentByEmailAddress("ignacio.garcia@gmail.com");

        // Assert created student is the same as the one found by email address
        assert collegeStudentEntity.getEmailAddress().equals("ignacio.garcia@gmail.com");

        // Delete student
        studentGradeServiceMock.deleteStudent(collegeStudentEntity.getId());

        // Assert student is null
        assertFalse(studentGradeServiceMock.isStudentNullCheck(collegeStudentEntity.getId()));
    }

    /**
     * Get grade book from service
     */
    @Sql(scripts = "classpath:InsertData.sql")
    @Test
    public void getGradeBookServiceTest() {
        // Get grade book from service in an Iterable CollegeStudentEntity and convert to list
        Iterable<CollegeStudentEntity> collegeStudentEntities = studentGradeServiceMock.getCollegeStudentsIterable();

        // Convert collegeStudentEntities to list
        List<CollegeStudentEntity> collegeStudentList = StreamSupport
                .stream(collegeStudentEntities.spliterator(), false)
                .toList();

        // Assert list is not empty
        assertEquals(collegeStudentList.size(), 11);
    }

    @Test
    public void createStudentGradebookTest() {
        // Create new grade book
        studentGradeServiceMock.createNewHistoryGradeBook();

        // Verify that get history grade book by student id is called
        Iterable<HistoryGrade> historyGradeList = studentGradeServiceMock.getHistoryGradeBookByStudentId(1);

        // assert that history grade book is not empty
        assertTrue(historyGradeList.iterator().hasNext(), "Student grade book is not empty");
    }

    @AfterEach
    public void tearDown() {
        // Delete all data from database
        jdbcTemplate.execute("delete from student");
    }
}
