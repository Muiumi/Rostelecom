package com.muyumi.RTKDataStructures.repositories;

import com.muyumi.RTKDataStructures.entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
