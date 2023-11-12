package com.muyumi.RTKDataStructures.repositories;

import com.muyumi.RTKDataStructures.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
