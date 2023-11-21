package com.muyumi.RTKDataStructures.repositories;

import com.muyumi.RTKDataStructures.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> { }
