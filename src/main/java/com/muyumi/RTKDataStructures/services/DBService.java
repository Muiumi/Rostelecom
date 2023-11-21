package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    private final SubjectService subjectService;
    private final ClassroomService classroomService;
    private final StudentService studentService;
    private final GradeService gradeService;
    private final DataLoaderFromTextFile loader;

    @Getter
    private final static int BATCH_SIZE = 4000;

    @Getter
    @Setter
    private Student currentStudent;

    @Getter
    @Setter
    private Classroom currentClassroom;

    public DBService(SubjectService subjectService, ClassroomService classroomService, StudentService studentService, GradeService gradeService, DataLoaderFromTextFile loader) {
        this.subjectService = subjectService;
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.loader = loader;
    }

    public void loadData(List<String> fileData) {
        subjectService.loadData(loader.getSubjects());
        for (String dataRow : fileData) {
            String[] dataFromRow = dataRow.split(",");
            String[] gradesFromRow = Arrays.copyOfRange(dataFromRow, 4, dataFromRow.length);
            var student = new Student();
            classroomService.loadData(dataFromRow);
            studentService.loadData(dataFromRow, student);
            gradeService.loadData(gradesFromRow, student);
        }
    }
}
