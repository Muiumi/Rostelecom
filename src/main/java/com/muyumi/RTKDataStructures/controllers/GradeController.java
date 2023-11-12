package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    GradeService gradeService;

    @GetMapping("/{classroom_id}/averageGrades")
    public ResponseEntity  getAverageGradesForClassroom (@PathVariable int classroom_id){
        try {
            return ResponseEntity.ok(gradeService.getAverageGrades(classroom_id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Ошибка при подключении");
        }
    }


}
