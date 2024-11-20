package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.entities.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface GradesDaoRepository extends CrudRepository<HistoryGrade, Integer> {
    List<HistoryGrade> findAllByStudentId(int id);
}
