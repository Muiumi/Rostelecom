package com.muyumi.RTKDataStructures.services;

import java.util.List;

public interface EntityService<T> {

    default void saveData(List<T> entitiesList) {
        System.out.println("Данный метод необходимо реализовать при необходимости сохранения пачками (batch) в БД");
    }

    boolean entityIsPresent(Long id);

    T getOne(Long id);
}
