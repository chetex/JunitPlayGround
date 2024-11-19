package com.luv2code.springmvc;

import com.luv2code.springmvc.models.students.*;
import com.luv2code.springmvc.services.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.jdbc.core.*;
import org.springframework.mock.web.*;
import org.springframework.test.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.web.servlet.*;

import java.util.*;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class GradebookControllerTest {

    // Create request MockHTTP request static variable
    private static MockHttpServletRequest mockHttpServletRequest;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentGradeService studentGradeService;

    @BeforeAll
    public static void setUpAll() {
        // Create new MockHttpServletRequest
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setParameter("firstName", "Ignacio");
        mockHttpServletRequest.setParameter("lastName", "Garcia");
        mockHttpServletRequest.setParameter("emailAddress", "ignacio.garcia@gmail.com");
    }

    @BeforeEach
    public void setUp() {
        // Insert data into database
        jdbcTemplate.execute("insert into student values (1, 'Ignacio', 'Garcia', 'ignacio.garcia@gmail.com')");
    }

    /**
     * This method test the getGradebookController call HTTP GET method
     * @throws Exception
     */
    @Test
    public void getGradebookControllerTest() throws Exception {
        // call perform mock mvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    /**
     * This method creates two college students add to a list and when student service
     * then return a list of college student
     */
    @Test
    public void getStudentHTTPTest() throws Exception {
        // Create new college student
        CollegeStudentEntity collegeStudentEntity = new CollegeStudentEntity("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Create other college student
        CollegeStudentEntity collegeStudentEntity2 = new CollegeStudentEntity("Ignacio2    ", "Garcia", "ignacio.garcia@gmail.com");

        // Add these two college students to a list
        List<CollegeStudentEntity> collegeStudentEntities = Arrays.asList(collegeStudentEntity, collegeStudentEntity2);

        // When student get grade book then return previous list
        when(studentGradeService.getCollegeStudentsIterable()).thenReturn(collegeStudentEntities);

        // Assert iterable equals previous list with student service get grade book
        assertIterableEquals(collegeStudentEntities, studentGradeService.getCollegeStudentsIterable());
    }

    @AfterEach
    public void tearDown() {
        // Delete all data from database
        jdbcTemplate.execute("delete from student");
    }
}
