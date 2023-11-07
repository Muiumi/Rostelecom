/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import dataloaders.DataLoaderFromTextFile;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @author Timkov Anton
 */
public class Person {

    private final String firstName, surname;
    private final int age, group;
    private final HashMap<String, Integer> personGradesDict = new HashMap<>();
    private double studentPerformance;

    public Person(String[] personData) {
        this.firstName = personData[1];
        this.surname = personData[0];
        this.age = Integer.parseInt(personData[2]);
        this.group = Integer.parseInt(personData[3]);
        int[] personGrades = new int[6];
        for (var i = 4; i < personData.length; i++) {
            personGrades[i - 4] = Integer.parseInt(personData[i]);
        }
        for (int i = 0; i < personGrades.length; i++) {
            this.personGradesDict.put(DataLoaderFromTextFile.getSubjects()[i], personGrades[i]);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public int getPersonGrade(String key) {
        return personGradesDict.get(key);
    }

    public double getStudentPerformance() {
        return this.studentPerformance = personGradesDict.values().stream().mapToInt(Integer::intValue).average().orElse(Double.NaN);
    }

    public void setPersonGrade(String subject, int newGrade) {
        personGradesDict.put(subject, newGrade);
    }

}
