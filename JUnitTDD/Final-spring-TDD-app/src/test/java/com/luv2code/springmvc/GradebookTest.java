package com.luv2code.springmvc;

import com.luv2code.springmvc.models.students.CollegeStudentEntity;
import com.luv2code.springmvc.services.StudentGradeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class GradebookTest {

    @Autowired
    private StudentGradeService studentGradeService;

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
        studentGradeService.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeService.findCollegeStudentByEmailAddress("ignacio.garcia@gmail.com");

        // Assert created student is the same as the one found by email address
        assert collegeStudentEntity.getEmailAddress().equals("ignacio.garcia@gmail.com");
    }

    @Test
    public void isStudentNullCheckTest() {
        // Call student grade service method check if student is null sending id = 1 and assert student is not null
        assertTrue(studentGradeService.isStudentNullCheck(1));

        // Given another student with id = 2 and assert student is NULL (student is not created yet)
        assertFalse(studentGradeService.isStudentNullCheck(0));
    }

    @AfterEach
    public void tearDown() {
        // Delete all data from database
        jdbcTemplate.execute("delete from student");
    }
}
