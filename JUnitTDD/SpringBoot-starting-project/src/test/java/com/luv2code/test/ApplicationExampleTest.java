package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    // Global logger
    private static final Logger logger = Logger.getLogger(ApplicationExampleTest.class.getName());

    // Set global count foe all test
    private Integer count = 0;

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    private CollegeStudent collegeStudent;

    @Autowired
    private StudentGrades studentGrades;

    @Autowired
    private ApplicationContext appContext;

    @BeforeEach
    public void BeforeEach () {
        count = count + 1;
        logger.log(Level.INFO, "Testing method number " + count + " version " + appVersion);
        collegeStudent.setFirstname("Nacho");
        collegeStudent.setLastname("Garcia");
        collegeStudent.setEmailAddress("test@test.com");
        studentGrades.setMathGradeResults(Arrays.asList(2.3, 3.4));
        collegeStudent.setStudentGrades(studentGrades);
    }

    /**
     * Assert equals and not equals
     */
    @Test
    public void testAddGradeResultsForStudentGrades () {
        assertEquals(5.699999999999999, studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()
        ));
    }

    /**
     * Assert not equals and not equals
     */
    @Test
    public void testAddGradeResultsForStudentGradesNotEquals () {
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()
        ));
    }

    /**
     * Assert not null
     */
    @Test
    public void testAddGradeResultsForStudentGradesNotNull () {
        assertNotNull(studentGrades.getMathGradeResults(), "Not null");
    }

    @Test
    public void testCreateStudentBean () {
        CollegeStudent collegeStudent1 = appContext.getBean(CollegeStudent.class);
        collegeStudent.setEmailAddress("test@test.com");
        assertNotNull(collegeStudent);
        assertNotSame(collegeStudent1, collegeStudent);

        assertAll("Testing all assert equals" ,
                () -> assertEquals(5.699999999999999, studentGrades.addGradeResultsForSingleClass(
                        collegeStudent.getStudentGrades().getMathGradeResults()
                )),
                () -> assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                        collegeStudent.getStudentGrades().getMathGradeResults()
                ))
        );
    }
}
