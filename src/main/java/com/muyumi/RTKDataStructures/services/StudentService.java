package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.exceptions.StudentNotFoundException;
import com.muyumi.RTKDataStructures.mappers.NewStudentMapper;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudentService implements EntityService<Student> {

    private final StudentRepository studentRepo;
    private final DBService dbService;
    private final ClassroomService classroomService;
    private final NewStudentMapper mapper;
    private final GradeService gradeService;

    @Override
    public boolean entityIsPresent(Long id) {
        return studentRepo.findById(id).isPresent();
    }

    @Override
    public Student getOne(Long id) {
        return studentRepo.findById(id).get();
    }

    public void editStudentGrade(Long studentId, String subjectName, int gradeValue) throws StudentNotFoundException {
        if (entityIsPresent(studentId)) {
            var student = getOne(studentId);
            for (var grade : student.getGradesList()) {
                if (grade.getSubject().getName().toLowerCase().equals(subjectName)) {
                    grade.setGrade(gradeValue);
                    studentRepo.save(student);
                    break;
                }
            }
        } else {
            throw new StudentNotFoundException("Студента с идентификатором " + studentId + " не существует.");
        }
    }

    public ResponseEntity<String> addStudent(NewStudentDTO studentDTO) throws ClassNotFoundException {
        if (classroomService.entityIsPresent(studentDTO.getClassroomNum())) {
            var student = mapper.toStudent(studentDTO);
            var classroom = classroomService.getOne(studentDTO.getClassroomNum());
            student.setClassroom(classroom);
            studentRepo.saveAndFlush(student);
            var gradesFromString = studentDTO.getGradesRow().split(",");
            gradeService.saveData(dbService.createGradesFromFile(gradesFromString, student, classroom));
            return ResponseEntity.ok("Студент успешно добавлен с идентификатором " + student.getId());
        } else {
            throw new ClassNotFoundException("Учебной группы с идентификатором " + studentDTO.getClassroomNum() + " не существует.");
        }
    }
}
