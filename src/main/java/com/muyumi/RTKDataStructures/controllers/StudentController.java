package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.services.GradeService;
import com.muyumi.RTKDataStructures.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    GradeService gradeService;


    @PutMapping("/{student_id}/updateGrade/{subject_name}/{grade}")
    public ResponseEntity <String> editStudentGrade (@PathVariable Long student_id, @PathVariable String subject_name, @PathVariable int grade){
        try {
            studentService.editStudentGrade(student_id,subject_name,grade);
            return ResponseEntity.ok("Оценка успешно изменена");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Ошибка при изменении");
        }
    }

}
