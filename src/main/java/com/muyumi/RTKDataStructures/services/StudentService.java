package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.exceptions.StudentNotFoundException;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService implements EntityService<Student> {

    private final StudentRepository studentRepo;
    private final GradeService gradeService;
    private final ClassroomService classroomService;

    private int count = 0;
    private final ArrayList<Student> students = new ArrayList<>();

    public StudentService(StudentRepository studentRepo, GradeService gradeService, ClassroomService classroomService) {
        this.studentRepo = studentRepo;
        this.gradeService = gradeService;
        this.classroomService = classroomService;
    }


    @Override
    public void loadData(String[] studentData, Student currentStudent) {
        currentStudent.setSurname(studentData[0]);
        currentStudent.setFirstName(studentData[1]);
        currentStudent.setAge(Integer.parseInt(studentData[2]));
        currentStudent.setClassroom(classroomService.getCurrentClassroom());
        students.add(currentStudent);
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            saveData();
        }
    }

    @Override
    public void saveData() {
        count = 0;
        studentRepo.saveAll(students);
        studentRepo.flush();
        students.clear();
    }

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
            Student student = getOne(studentId);
            for (Grade grade : student.getGradesList()) {
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
            var student = new Student();
            student.setSurname(studentDTO.getSurname());
            student.setFirstName(studentDTO.getFirstName());
            student.setAge(studentDTO.getAge());
            student.setClassroom(classroomService.getOne(studentDTO.getClassroomNum()));
            studentRepo.saveAndFlush(student);
            String[] gradesFromString = studentDTO.getGradesRow().split(",");
            gradeService.loadData(gradesFromString, student);
            gradeService.saveData();
            return ResponseEntity.ok("Студент успешно добавлен с идентификатором " + student.getId());
        } else {
            throw new ClassNotFoundException("Учебной группы с идентификатором " + studentDTO.getClassroomNum() + " не существует.");
        }
    }
}
