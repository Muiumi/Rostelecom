package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService implements IService<String> {

    @Autowired
    private StudentRepository studentRepo;

    private int count = 0;
    private final ArrayList<Student> students = new ArrayList<>();


    @Override
    public void loadData(String dataRow) {
        var student = new Student();
        student.setSurname(dataRow.split(",")[0]);
        student.setFirstName(dataRow.split(",")[1]);
        student.setAge(Integer.parseInt(dataRow.split(",")[2]));
        student.setClassroom(DBService.getCurrentClassroom());
        DBService.setCurrentStudent(student);
        students.add(student);
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            studentRepo.saveAll(students);
            studentRepo.flush();
            students.clear();
        }
    }

    public void editStudentGrade(Long studentId, String subjectName, int gradeValue) {
        if (studentRepo.findById(studentId).isPresent()) {
            Student student = studentRepo.findById(studentId).get();
            for (Grade grade : student.getGradesList()) {
                if (grade.getSubject().getName().equalsIgnoreCase(subjectName)) {
                    grade.setGrade(gradeValue);
                    studentRepo.save(student);
                    break;
                }
            }
        }
    }

}
