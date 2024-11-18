package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.students.CollegeStudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentDaoRepository extends CrudRepository<CollegeStudentEntity, Integer> {
    CollegeStudentEntity findByEmailAddress(String emailAddress);
}
