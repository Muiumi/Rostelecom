package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/db")
public class CoreController {

    @Autowired
    DBService service;

    @GetMapping("loadDB")
    public ResponseEntity loadDB (){
        try {
            service.loadData();
            return ResponseEntity.ok("Успешная загрузка в БД");

        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Возникла ошибка " + ex.getMessage() + ex.getLocalizedMessage() + Arrays.toString(ex.getStackTrace()));
        }
    }
}
