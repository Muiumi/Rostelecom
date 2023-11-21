package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.ClassroomRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassroomService implements EntityService<Classroom> {

    private final SubjectService subjectService;
    private final ClassroomRepository classroomRepo;
    @Getter
    private Classroom currentClassroom;

    public ClassroomService(SubjectService subjectService, ClassroomRepository classroomRepo) {
        this.subjectService = subjectService;
        this.classroomRepo = classroomRepo;
    }

    @Override
    public void loadData(String[] dataRow) {
        if (classroomRepo.findById(Long.parseLong(dataRow[3])).isEmpty()) {
            var classroom = new Classroom();
            classroom.setId(Long.parseLong(dataRow[3]));
            var classroomSubjectsList = new ArrayList<Subject>();
            for (Subject subject : subjectService.findAllEntities()) {
                classroomSubjectsList.add(subject);
            }
            classroom.setSubjects(classroomSubjectsList);
            classroomRepo.save(classroom);
        }
        currentClassroom = getOne(Long.parseLong(dataRow[3]));
    }

    @Override
    public boolean entityIsPresent(Long id) {
        return classroomRepo.findById(id).isPresent();
    }

    @Override
    public Classroom getOne(Long id) {
        return classroomRepo.findById(id).get();
    }
}



