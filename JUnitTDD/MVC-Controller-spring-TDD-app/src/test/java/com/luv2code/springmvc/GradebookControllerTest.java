package com.luv2code.springmvc;

import com.luv2code.springmvc.models.students.*;
import com.luv2code.springmvc.services.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.jdbc.core.*;
import org.springframework.mock.web.*;
import org.springframework.test.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.web.servlet.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private StudentGradeService studentGradeServiceMock;

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

    @Test
    public void createPostStudentTest() throws Exception {
        checkCurrentStudentIsCreasted();

        // Call mockMvc perform post request with content type application/json
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", mockHttpServletRequest.getParameter("firstName"))
                        .param("lastName", mockHttpServletRequest.getParameter("lastName"))
                        .param("emailAddress", mockHttpServletRequest.getParameter("emailAddress")))
                .andExpect(status().isOk())
                .andReturn();

        // Get model and view and assert view name index
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        // Call student grade service find by email address
        when(studentGradeServiceMock.findCollegeStudentByEmailAddress(mockHttpServletRequest.getParameter("emailAddress")))
                .thenReturn(new CollegeStudentEntity("Ignacio", "Garcia", "ignacio.garcia@gmail.com"));

        // Call student grade service is student null check
        when(studentGradeServiceMock.isStudentNullCheck(1))
                .thenReturn(true);

        // Call student grade service delete student
        studentGradeServiceMock.deleteStudent(1);
    }

    /**
     * This method check if current student is created correctly
     */
    private void checkCurrentStudentIsCreasted() {
        // Create college student entity
        CollegeStudentEntity collegeStudentEntity = new CollegeStudentEntity("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Add college student entity to a list
        List<CollegeStudentEntity> collegeStudentEntities = Arrays.asList(collegeStudentEntity);

        // When student get grade book then return previous list
        when(studentGradeServiceMock.getCollegeStudentsIterable()).thenReturn(collegeStudentEntities);

        // Assert iterable equals previous list with student service get grade book
        assertIterableEquals(collegeStudentEntities, studentGradeServiceMock.getCollegeStudentsIterable());
    }

    /**
     * This method creates two college students add to a list and when student service
     * then return a list of college student
     */
    @Test
    public void getStudentDaoRepositoryTest() throws Exception {
        // Create new college student
        CollegeStudentEntity collegeStudentEntity = new CollegeStudentEntity("Ignacio", "Garcia", "ignacio.garcia@gmail.com");

        // Create other college student
        CollegeStudentEntity collegeStudentEntity2 = new CollegeStudentEntity("Ignacio2    ", "Garcia", "ignacio.garcia@gmail.com");

        // Add these two college students to a list
        List<CollegeStudentEntity> collegeStudentEntities = Arrays.asList(collegeStudentEntity, collegeStudentEntity2);

        // When student get grade book then return previous list
        when(studentGradeServiceMock.getCollegeStudentsIterable()).thenReturn(collegeStudentEntities);

        // Assert iterable equals previous list with student service get grade book
        assertIterableEquals(collegeStudentEntities, studentGradeServiceMock.getCollegeStudentsIterable());
    }

    /**
     * Create test for delete student method
     */
    @Test
    public void deleteStudentTest() {
        // Call student grade service to delete student
        studentGradeServiceMock.deleteStudent(1);

        // Verify that student is deleted
        verify(studentGradeServiceMock, times(1)).deleteStudent(1);
    }

    /**
     * Delete student by http delete request
     */
    @Test
    public void deleteStudentByHttpDeleteRequestTest() throws Exception {
        // Mockmvc perform http get request to delete studen url and get result
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/delete/student/{1}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Get model and view from mvc result
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // Verify that view is studentInformation
        assert modelAndView != null;
        assertEquals("index", modelAndView.getViewName());
    }

    @AfterEach
    public void tearDown() {
        // Delete all data from database
        jdbcTemplate.execute("delete from student");
    }
}
