package com.luv2code.springmvc;

import com.luv2code.springmvc.models.students.CollegeStudentEntity;
import com.luv2code.springmvc.services.StudentGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class GradebookTest {

    @Autowired
    private StudentGradeService studentGradeService;

    @Test
    public void createStudentServiceTest() {
        // Call student service and create new student
        studentGradeService.createStudent("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Find college student by email address
        CollegeStudentEntity collegeStudentEntity = studentGradeService.findCollegeStudentByEmailAddress("ignacio.garcia@gmail.com");

        // Assert created student is the same as the one found by email address
        assert collegeStudentEntity.getEmailAddress().equals("ignacio.garcia@gmail.com");
    }
}
