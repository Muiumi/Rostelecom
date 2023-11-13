package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.entities.Subject;
import com.muyumi.RTKDataStructures.exceptions.GradesAlreadyExistException;
import com.muyumi.RTKDataStructures.exceptions.StudentNotFoundException;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import com.muyumi.RTKDataStructures.requestmodels.NewStudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService implements EntityService<Student> {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ClassroomService classroomService;

    private int count = 0;
    private final ArrayList<Student> students = new ArrayList<>();


    @Override
    public void loadData(String[] studentData) {
        var student = new Student();
        student.setSurname(studentData[0]);
        student.setFirstName(studentData[1]);
        student.setAge(Integer.parseInt(studentData[2]));
        student.setClassroom(DBService.getCurrentClassroom());
        DBService.setCurrentStudent(student);
        students.add(student);
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

    public ResponseEntity<String> addStudent(NewStudentModel newStudent) throws ClassNotFoundException {
        if (classroomService.entityIsPresent(newStudent.getClassroom_num())) {
            var student = new Student();
            student.setSurname(newStudent.getSurname());
            student.setFirstName(newStudent.getFirst_name());
            student.setAge(newStudent.getAge());
            student.setClassroom(classroomService.getOne(newStudent.getClassroom_num()));
            studentRepo.saveAndFlush(student);
            return ResponseEntity.ok("Указанный студент успешно сохранён под следующим идентификатором: " + student.getId() + ". Однако необходимо заполнить оценки для указанного студента по следующим предметам: " +
                    classroomService.getOne(newStudent.getClassroom_num()).getSubjects().stream().map(Subject::getName).toList() + "\n Для загрузки оценок студента воспользуйтесь командой /student-addition/{student_id} c параметром grades, содержащем оценки через запятую");
        } else {
            throw new ClassNotFoundException("Учебной группы с идентификатором " + newStudent.getClassroom_num() + " не существует.");
        }
    }

    public ResponseEntity<String> addGradesToStudent(Long studentId, String grades) throws StudentNotFoundException, GradesAlreadyExistException {
        if (entityIsPresent(studentId)) {
            Student studentById = getOne(studentId);
            if (studentById.getGradesList().isEmpty()) {
                String[] gradesFromString = grades.split(",");
                DBService.setCurrentStudent(studentById);
                DBService.setCurrentClassroom(studentById.getClassroom());
                gradeService.loadData(gradesFromString);
                gradeService.saveData();
                return ResponseEntity.ok("Оценки успешно добавлены студенту");
            } else {
                throw new GradesAlreadyExistException("Оценки для студента с идентификатором " + studentId + "уже заполнены.");
            }
        } else {
            throw new StudentNotFoundException("Студента с идентификатором " + studentId + " не существует.");
        }
    }
}
