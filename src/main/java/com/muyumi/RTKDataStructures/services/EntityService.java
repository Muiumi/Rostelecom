package com.muyumi.RTKDataStructures.services;

import jakarta.persistence.Entity;

public interface EntityService<T> {
    void loadData(String[] dataArray);

    default void saveData(){
        System.out.println("Данный метод необходимо реализовать при необходимости сохранения в БД");
    }
    boolean entityIsPresent (Long id);

    T getOne (Long id);
}
