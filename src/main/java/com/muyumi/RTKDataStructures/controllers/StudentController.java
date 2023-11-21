package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import com.muyumi.RTKDataStructures.exceptions.StudentNotFoundException;
import com.muyumi.RTKDataStructures.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/{student_id}/grade-change/{subject_name}/{grade}")
    public ResponseEntity<String> editStudentGrade(@PathVariable Long student_id, @PathVariable String subject_name, @PathVariable int grade) throws StudentNotFoundException {
        studentService.editStudentGrade(student_id, subject_name, grade);
        return ResponseEntity.ok("Оценка успешно изменена");
    }

    @PostMapping("/student-addition")
    public ResponseEntity<String> addStudent(@RequestBody NewStudentDTO studentModel) throws ClassNotFoundException {
        return studentService.addStudent(studentModel);
    }
}
