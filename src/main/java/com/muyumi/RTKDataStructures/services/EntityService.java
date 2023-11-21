package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Student;

public interface EntityService<T> {
    default void loadData(String[] dataArray) {
        System.out.println("Данный метод необходимо реализовать при необходимости передачи в метод-загрузчика в БД только строку данных");
    }

    default void loadData(String[] dataArray, Student student) {
        System.out.println("Данный метод необходимо реализовать при необходимости передачи в метод-загрузчика в БД сущность, представляющую студента вместе со строкой данных");
    }

    default void saveData() {
        System.out.println("Данный метод необходимо реализовать при необходимости сохранения пачками (batch) в БД");
    }

    boolean entityIsPresent(Long id);

    T getOne(Long id);
}
