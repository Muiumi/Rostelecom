package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class SubjectService implements EntityService<Subject> {
    private final SubjectRepository subjectRepo;

    @Override
    public boolean entityIsPresent(Long id) {
        return subjectRepo.findById(id).isPresent();
    }

    @Override
    public Subject getOne(Long id) {
        return subjectRepo.findById(id).get();
    }

    public ArrayList<Subject> getAllEntities() {
        var subjectList = new ArrayList<Subject>();
        subjectRepo.findAll().forEach(subjectList::add);
        return subjectList;
    }
}

