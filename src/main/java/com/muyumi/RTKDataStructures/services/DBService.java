package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;


@Component
@Service
public class DBService {

    @Autowired
    SubjectService subjectService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    private DataLoaderFromTextFile loader;

    @Getter
    private final static int BATCH_SIZE = 4000;

    @Getter
    @Setter
    private static Student currentStudent;

    @Getter
    @Setter
    private static Classroom currentClassroom;


    public void loadData(ArrayList<String> fileData) {
        subjectService.loadData(loader.getSubjects());
        for (String dataRow : fileData) {
            String[] dataFromRow = dataRow.split(",");
            String[] gradesFromRow = Arrays.copyOfRange(dataFromRow, 4, dataFromRow.length);
            classroomService.loadData(dataFromRow);
            studentService.loadData(dataFromRow);
            gradeService.loadData(gradesFromRow);
        }
    }
}
