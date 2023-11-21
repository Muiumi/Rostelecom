package com.muyumi.RTKDataStructures.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {
    @ExceptionHandler(ClassroomNotFoundException.class)
    public ResponseEntity<String> handlerForClassroomNotFoundException(ClassroomNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Произошла ошибка: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handlerForStudentNotFoundException(StudentNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Произошла ошибка: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handlerForStudentNotFoundException(FileNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Произошла ошибка: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handlerForIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Произошла ошибка: Предметов добавлено больше, чем заявлено у учебной группы " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
