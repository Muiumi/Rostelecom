package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
public class SubjectService implements IService <String[]> {
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private DataLoaderFromTextFile loader;

    @Override
    public void loadData(String[] subjects) {
        for (String subjectName : subjects) {
            var subject = new Subject();
            subject.setName(subjectName);
            subjectRepo.save(subject);
        }
    }
}
