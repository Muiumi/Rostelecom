package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements EntityService<Subject> {
    private final SubjectRepository subjectRepo;

    public SubjectService(SubjectRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

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

    public Iterable<Subject> findAllEntities() {
        return subjectRepo.findAll();
    }
}
