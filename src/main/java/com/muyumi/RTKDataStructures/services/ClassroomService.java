package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.repositories.ClassroomRepository;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassroomService implements IService<String> {

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private ClassroomRepository classroomRepo;

    private int count = 0;
    private final ArrayList<Classroom> classrooms = new ArrayList<>();

    @Override
    public void loadData(String dataRow) {
        var classroom = new Classroom();
        classroom.setId(Integer.parseInt(dataRow.split(",")[3]));
        var classroomSubjectsList = new ArrayList<Subject>();
        for (Subject subject : subjectRepo.findAll()) {
            classroomSubjectsList.add(subject);
        }
        classroom.setSubjects(classroomSubjectsList);
        classrooms.add(classroom);
        DBService.setCurrentClassroom(classroom);
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            classroomRepo.saveAll(classrooms);
            classroomRepo.flush();
            classrooms.clear();
        }
    }
}



