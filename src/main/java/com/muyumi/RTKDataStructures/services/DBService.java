package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import com.muyumi.RTKDataStructures.entities.Classroom;
import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.mappers.NewStudentMapper;
import com.muyumi.RTKDataStructures.repositories.ClassroomRepository;
import com.muyumi.RTKDataStructures.repositories.GradeRepository;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import com.muyumi.RTKDataStructures.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DBService {

    private final SubjectService subjectService;
    private final ClassroomService classroomService;
    private final DataLoaderFromTextFile loader;
    private final NewStudentMapper mapper;

    private final ClassroomRepository classroomRepo;
    private final StudentRepository studentRepo;
    private final GradeRepository gradeRepo;
    private final SubjectRepository subjectRepo;

    private boolean subjectsAdded = false;
    private final List<Student> studentsList = new ArrayList<>();
    private final List<Grade> gradesList = new ArrayList<>();

    public void loadData(List<NewStudentDTO> fileData) {
        // Добавляем предметы
        if (!subjectsAdded) {
            for (var subjectName : loader.getSubjects()) {
                var subject = new Subject();
                subject.setName(subjectName);
                subjectRepo.save(subject);
            }
            subjectsAdded = true;
        }
        // Обрабатываем dto
        for (var i = 0; i < fileData.size(); i++) {
            var studentDTO = fileData.get(i);
            // Добавляем учебную группу при её отсутствии
            Classroom currentClassroom;
            if (!(classroomService.entityIsPresent(studentDTO.getClassroomNum()))) {
                currentClassroom = new Classroom();
                currentClassroom.setId(studentDTO.getClassroomNum());
                currentClassroom.setSubjects(subjectService.getAllEntities());
                classroomRepo.saveAndFlush(currentClassroom);
            } else {
                currentClassroom = classroomService.getOne(studentDTO.getClassroomNum());
            }
            // Добавляем студента
            var student = mapper.toStudent(studentDTO);
            student.setClassroom(currentClassroom);
            studentsList.add(student);
            // Добавляем оценки студента
            String[] gradesFromRow = studentDTO.getGradesRow().split(",");
            gradesList.addAll(createGradesFromFile(gradesFromRow, student, currentClassroom));
            // Пакетная проверка
            if (i % 4000 == 0 || i == fileData.size() - 1) {
                studentRepo.saveAllAndFlush(studentsList);
                studentsList.clear();
                gradeRepo.saveAllAndFlush(gradesList);
                gradesList.clear();
            }
        }
    }

    protected List<Grade> createGradesFromFile(String[] gradesFromRow, Student student, Classroom classroom) {
        var gradesForStudentList = new ArrayList<Grade>();
        for (var currentGrade = 0; currentGrade < gradesFromRow.length; currentGrade++) {
            var grade = new Grade();
            grade.setGrade(Integer.parseInt(gradesFromRow[currentGrade]));
            grade.setStudent(student);
            grade.setSubject(classroom.getSubjects().get(currentGrade));
            gradesForStudentList.add(grade);
        }
        return gradesForStudentList;
    }
}
