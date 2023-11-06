/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import dataloaders.IDataLoader;

import java.util.List;

/**
 * @author Timkov Anton
 */
public class StudentService implements IService<DataGroup> {
//Сервис для загрузки структур и проверки их наличия

    private final IDataLoader<Person> fileDataLoader;
    private final DataGroup[] dataGroupsArray;

    public StudentService(IDataLoader<Person> fileDataLoader) {
        this.fileDataLoader = fileDataLoader;
        DataGroup ageDataGroup = null;
        DataGroup nameDataGroup = null;
        DataGroup classroomDataGroup = null;
        this.dataGroupsArray = new DataGroup[]{ageDataGroup, classroomDataGroup, nameDataGroup};
    }

    public List<Person> loadDataFromLoader() {
        return this.fileDataLoader.loadData();
    }

    public DataGroup[] getDataGroup() {
        return dataGroupsArray;
    }

    @Override
    public void loadDataInSelectedStructure(DataGroup currentDataGroup) {
        for (Person person : loadDataFromLoader()) {
            currentDataGroup.addPerson(person);
        }
    }
}
