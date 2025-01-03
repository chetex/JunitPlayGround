package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.components.*;
import com.luv2code.springmvc.entities.*;
import com.luv2code.springmvc.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentGradeService studentGradeService;

	@GetMapping("/")
	public String getStudents(Model model) {
		Iterable<CollegeStudentEntity> collegeStudentEntities = studentGradeService.getCollegeStudentsIterable();
		model.addAttribute("students", collegeStudentEntities);
		return "index";
	}

	@GetMapping("/studentInformation/{id}")
	public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
	}

	/**
	 * When receive a post request with content type application/json
	 * then create a new student
	 * @param student college student entity ModelAttribute
	 * @return view name index String
	 */
	@PostMapping("/")
	public String createStudent(@ModelAttribute("student") CollegeStudentEntity student, Model model) {
		studentGradeService.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());

		// Also get grade book into iterable and then add attribute with this iterable
		Iterable<CollegeStudentEntity> collegeStudentEntities = studentGradeService.getCollegeStudentsIterable();
		model.addAttribute("students", collegeStudentEntities);

		return "index";
	}

	/**
	 * Create a new get mapping for the delete student
	 * @param id The id of the student
	 * @return view name index String
	 */
	@GetMapping("/delete/student/{id}")
	public String deleteStudent(@PathVariable int id, Model model) {
		// Delete student
		studentGradeService.deleteStudent(id);

		// Also get grade book into iterable and then add attribute with this iterable
		Iterable<CollegeStudentEntity> collegeStudentEntities = studentGradeService.getCollegeStudentsIterable();
		model.addAttribute("students", collegeStudentEntities);

		return "index";
	}
}
