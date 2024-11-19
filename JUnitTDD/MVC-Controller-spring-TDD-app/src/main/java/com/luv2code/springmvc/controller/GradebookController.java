package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.components.Gradebook;
import com.luv2code.springmvc.models.students.*;
import com.luv2code.springmvc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
