package com.muyumi.RTKDataStructures.repositories;

import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
