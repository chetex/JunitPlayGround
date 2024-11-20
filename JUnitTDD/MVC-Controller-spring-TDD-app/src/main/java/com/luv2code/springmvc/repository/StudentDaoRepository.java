package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.entities.CollegeStudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDaoRepository extends CrudRepository<CollegeStudentEntity, Integer> {
    CollegeStudentEntity findByEmailAddress(String emailAddress);
}
