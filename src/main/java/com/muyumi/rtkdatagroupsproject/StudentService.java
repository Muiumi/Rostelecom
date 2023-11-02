/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import dataloaders.DataLoader;

import java.util.ArrayList;

/**
 * @author Timkov Anton
 */
public class StudentService {
//Сервис для загрузки структур и проверки их наличия

    private final DataLoader<Person> fileDataLoader;
    private ArrayList<Person> personsList = null;
    private final DataGroup[] dataGroupsArray;

    public StudentService(DataLoader<Person> fileDataLoader) {
        this.fileDataLoader = fileDataLoader;
        DataGroup ageDataGroup = null;
        DataGroup nameDataGroup = null;
        DataGroup classroomDataGroup = null;
        this.dataGroupsArray = new DataGroup[]{ageDataGroup, classroomDataGroup, nameDataGroup};
    }

    public void loadDataFromLoader() {
        this.personsList = this.fileDataLoader.loadData();
    }

    public DataGroup[] getDataGroup() {
        return dataGroupsArray;
    }

    public void loadDataGroup(DataGroup currentDataGroup) {
        for (Person person : personsList) {
            currentDataGroup.addPerson(person);
        }
    }
}
