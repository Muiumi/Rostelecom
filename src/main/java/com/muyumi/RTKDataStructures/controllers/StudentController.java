package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository personRepo;
    @PostMapping
    public ResponseEntity addPerson (@RequestBody Student person){
        try {
            personRepo.save(person);
            return ResponseEntity.ok("Успешное добавление студента");

        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Ошибка при добавлении");
        }
    }
    @GetMapping("/")
    public ResponseEntity getPersons (){
       try {
           return ResponseEntity.ok("Успешное подключение");

       }catch (Exception ex){
           return ResponseEntity.badRequest().body("Ошибка при подключении");
       }
    }

}
