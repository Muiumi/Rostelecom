package com.muyumi.RTKDataStructures.repositories;

import com.muyumi.RTKDataStructures.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentClassroomId(Long classroomId);
}
