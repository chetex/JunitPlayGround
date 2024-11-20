package com.luv2code.springmvc.services;

import com.luv2code.springmvc.entities.*;
import com.luv2code.springmvc.repository.*;
import com.querydsl.jpa.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.util.*;

@Service
@Transactional
public class StudentGradeService {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentDaoRepository studentDaoRepository;

    @Autowired
    private GradesDaoRepository gradesDaoRepository;

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
            entityManager.persist(collegeStudent);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    /**
     * Update student given the id and the new values
     * @param id The id of the student
     */
    public Boolean updateStudent(int id) {
        boolean exit = false;
        try {
            long filasActualizadas = new JPAUpdateClause(entityManager, QCollegeStudentEntity.collegeStudentEntity)
                    .where(QCollegeStudentEntity.collegeStudentEntity.id.eq(id))
                    .set(QCollegeStudentEntity.collegeStudentEntity.firstname, "Ignacio 2")
                    .execute();

            exit = filasActualizadas > 0;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return exit;
    }

    /**
     * Delete student given the id
     * @param id The id of the student
     */
    public Boolean deleteStudentWithJPA(int id) {
        long filasEliminadas = 0L;
        try {
            filasEliminadas = new JPADeleteClause(entityManager, QCollegeStudentEntity.collegeStudentEntity)
                    .where(QCollegeStudentEntity.collegeStudentEntity.id.eq(id))
                    .execute();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return filasEliminadas > 0;
    }

    /**
     * Find college student by email address
     * @param emailAddress The email address of the student
     * @return The college student
     */
    public CollegeStudentEntity findCollegeStudentByEmailAddress(String emailAddress) {
        return (CollegeStudentEntity) queryFactory
                .from(QCollegeStudentEntity.collegeStudentEntity)
                .where(QCollegeStudentEntity.collegeStudentEntity.emailAddress.eq(emailAddress))
                .orderBy(QCollegeStudentEntity.collegeStudentEntity.id.asc())
                .fetchOne();
    }

    /**
     * Find college student by id
     * @param id The id of the student
     * @return The college student
     */
    public CollegeStudentEntity findCollegeStudentByID(int id) {
        return (CollegeStudentEntity) queryFactory
                .from(QCollegeStudentEntity.collegeStudentEntity)
                .where(QCollegeStudentEntity.collegeStudentEntity.id.eq(id))
                .orderBy(QCollegeStudentEntity.collegeStudentEntity.id.asc())
                .fetchOne();
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

    /**
     * Create new history grade book
     */
    public void createNewHistoryGradeBook() {
        try {
            // Create new history grade book
            HistoryGrade historyGrade = new HistoryGrade();
            historyGrade.setStudentId(1);
            historyGrade.setGrade(0);

            // Save history grade book
            gradesDaoRepository.save(historyGrade);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    /**
     * Get history grade book by student id
     * @param id The id of the student
     * @return List<HistoryGrade> The history grade book list
     */
    public List<HistoryGrade> getHistoryGradeBookByStudentId(int id) {
        List<HistoryGrade> historyGradeList = null;
        try {
            // Get history grade book by student id
            historyGradeList = gradesDaoRepository.findAllByStudentId(id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return historyGradeList;
    }
}

// Update student using JPAUpdateClause and entity manager
//            entityManager.merge(entityManager.createQuery("update CollegeStudentEntity set firstname = :firstname, lastname = :lastname, emailAddress = :emailAddress where id = :id")
//                    .setParameter("firstname", "Ignacio 2")
//                    .setParameter("lastname", "Garcia 2")
//                    .setParameter("emailAddress", "ignacio.garcia@gmail.com")
//                    .setParameter("id", id)
//                    .executeUpdate());