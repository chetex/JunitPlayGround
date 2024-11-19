package com.luv2code.springmvc.services;

import com.luv2code.springmvc.models.students.CollegeStudentEntity;
import com.luv2code.springmvc.repository.StudentDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentGradeService {

    @Autowired
    private StudentDaoRepository studentDaoRepository;

    /**
     * Create student given the firstname, lastname and email address
     * @param name The first name of the student
     * @param surname The last name of the student
     * @param mail The email address of the student
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

    /**
     * Find college student by email address
     * @param emailAddress The email address of the student
     * @return The college student
     */
    public CollegeStudentEntity findCollegeStudentByEmailAddress(String emailAddress) {
        return studentDaoRepository.findByEmailAddress(emailAddress);
    }

    /**
     * Check if student is null
     * @param i The id of the student
     * @return true if student is null, false otherwise
     */
    public boolean isStudentNullCheck(int i) {
        boolean exit = false;
        try {
            // Find college student by id
            Optional<CollegeStudentEntity> collegeStudentOptional = studentDaoRepository.findById(i);

            // If student is present, student is not null
            if (collegeStudentOptional.isPresent()) {
                exit = true;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return exit;
    }

    /**
     * Delete student given the id
     * @param id The id of the student
     */
    public void deleteStudent(int id) {
        try {
            // Find college student by id
            Optional<CollegeStudentEntity> collegeStudentOptional = studentDaoRepository.findById(id);

            // If student is present, student is not null
            if (collegeStudentOptional.isPresent()) {
                // Delete student
                studentDaoRepository.delete(collegeStudentOptional.get());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    /**
     * Get grade book
     * @return Iterable<CollegeStudentEntity> The grade book
     */
    public Iterable<CollegeStudentEntity> getCollegeStudentsIterable() {
        Iterable<CollegeStudentEntity> collegeStudentEntities = null;
        try {
            // Get grade book from repository
            collegeStudentEntities = studentDaoRepository.findAll();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return collegeStudentEntities;
    }
}
