package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClassroomService implements EntityService<Classroom> {

    private final ClassroomRepository classroomRepo;

    @Override
    public boolean entityIsPresent(Long id) {
        return classroomRepo.findById(id).isPresent();
    }

    @Override
    public Classroom getOne(Long id) {
        return classroomRepo.findById(id).get();
    }
}



