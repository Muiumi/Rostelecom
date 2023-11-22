package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.exceptions.ClassroomNotFoundException;
import com.muyumi.RTKDataStructures.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/{classroom_id}/avg-grades")
    public ResponseEntity getAverageGradesForClassroom(@PathVariable Long classroom_id) throws ClassroomNotFoundException {
        return ResponseEntity.ok(gradeService.getAverageGrades(classroom_id));
    }
}

