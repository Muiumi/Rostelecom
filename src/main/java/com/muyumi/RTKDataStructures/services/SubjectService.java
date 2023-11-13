package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class SubjectService implements EntityService<Subject> {
    @Autowired
    private SubjectRepository subjectRepo;


    @Override
    public void loadData(String[] subjects) {
        for (String subjectName : subjects) {
            var subject = new Subject();
            subject.setName(subjectName);
            subjectRepo.save(subject);
        }
    }

    @Override
    public boolean entityIsPresent(Long id) {
        return subjectRepo.findById(id).isPresent();
    }

    @Override
    public Subject getOne(Long id) {
        return subjectRepo.findById(id).get();
    }

    public Iterable<Subject> findAllEnitities() {
        return subjectRepo.findAll();
    }
}
