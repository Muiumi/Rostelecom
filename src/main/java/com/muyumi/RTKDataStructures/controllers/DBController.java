package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DBController {

    @Autowired
    DBService service;
    @Autowired
    private DataLoaderFromTextFile loader;

    @GetMapping("loadDB")
    public ResponseEntity<String> loadDB() {
        try {
            service.loadData(loader.readDataFromFile());
            return ResponseEntity.ok("Успешная загрузка в БД");

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Возникла ошибка " + ex.getMessage());
        }
    }
}
