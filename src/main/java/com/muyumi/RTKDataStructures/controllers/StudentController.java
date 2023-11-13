package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.requestmodels.NewStudentModel;
import com.muyumi.RTKDataStructures.services.GradeService;
import com.muyumi.RTKDataStructures.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    GradeService gradeService;


    @PutMapping("/{student_id}/grade-change/{subject_name}/{grade}")
    public ResponseEntity<String> editStudentGrade(@PathVariable Long student_id, @PathVariable String subject_name, @PathVariable int grade) {
        try {
            studentService.editStudentGrade(student_id, subject_name, grade);
            return ResponseEntity.ok("Оценка успешно изменена");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/student-addition")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> addStudent(@RequestBody NewStudentModel studentModel) {
        try {
            return studentService.addStudent(studentModel);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/student-addition/{student_id}")
    public ResponseEntity<String> addGradesToStudent(@PathVariable Long student_id, @RequestParam String grades) {
        try {
            return studentService.addGradesToStudent(student_id, grades);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
