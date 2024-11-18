package com.luv2code.springmvc.services;

import com.luv2code.springmvc.models.students.CollegeStudentEntity;
import com.luv2code.springmvc.repository.StudentDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeService {

    @Autowired
    private StudentDaoRepository studentDaoRepository;

    /**
     * Create student given the firstname, lastname and email address
     * @param name
     * @param surname
     * @param mail
     */
    public void createStudent(String name, String surname, String mail) {
        try {
            // Create college student
            CollegeStudentEntity collegeStudent = new CollegeStudentEntity(name, surname, mail);

            // Create new student DAO and save college student
            studentDaoRepository.save(collegeStudent);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
