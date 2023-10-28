/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import java.util.ArrayList;

/**
 *
 * @author Timkov Anton
 */
public class StudentService {
//Сервис для загрузки структур и проверки их наличия

    private final DataLoader<Person> fileDataLoader;
    private final ArrayList<Person> personsList;
    private final DataGroup ageDataGroup = null;
    private final DataGroup nameDataGroup = null;
    private final DataGroup classroomDataGroup = null;
    private final DataGroup[] dataGroupsArray;

    public StudentService(DataLoader<Person> fileDataLoader) {
        this.fileDataLoader = fileDataLoader;
        this.personsList = fileDataLoader.loadData();
        this.dataGroupsArray = new DataGroup[]{ageDataGroup, classroomDataGroup, nameDataGroup};
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
