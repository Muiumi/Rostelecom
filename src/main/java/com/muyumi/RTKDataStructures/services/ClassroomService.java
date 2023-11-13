package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassroomService implements EntityService<Classroom> {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ClassroomRepository classroomRepo;

    private int count = 0;
    private final ArrayList<Classroom> classrooms = new ArrayList<>();

    @Override
    public void loadData(String[] dataRow) {
        var classroom = new Classroom();
        classroom.setId(Long.parseLong(dataRow[3]));
        var classroomSubjectsList = new ArrayList<Subject>();
        for (Subject subject : subjectService.findAllEnitities()) {
            classroomSubjectsList.add(subject);
        }
        classroom.setSubjects(classroomSubjectsList);
        classrooms.add(classroom);
        DBService.setCurrentClassroom(classroom);
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            saveData();
        }
    }

    @Override
    public void saveData() {
        count = 0;
        classroomRepo.saveAll(classrooms);
        classroomRepo.flush();
        classrooms.clear();
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



