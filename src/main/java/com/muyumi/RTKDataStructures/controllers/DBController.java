package com.muyumi.RTKDataStructures.controllers;

import com.muyumi.RTKDataStructures.dataloaders.DataLoaderFromTextFile;
import com.muyumi.RTKDataStructures.services.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students-data")
public class DBController {

    private final DBService service;
    private final DataLoaderFromTextFile loader;

    @GetMapping("db-init")
    public ResponseEntity<String> loadDB() throws FileNotFoundException {
        service.loadData(loader.readDataFromFile());
        return ResponseEntity.ok("Успешная загрузка в БД");
    }
}
