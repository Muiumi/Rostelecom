/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import java.util.stream.IntStream;

/**
 * @author Timkov Anton
 */
public class Person {

    private final String firstName, surname;
    private final int age, group;
    private final int[] personGrades = new int[6];


    public Person(String[] personData) {
        this.firstName = personData[1];
        this.surname = personData[0];
        this.age = Integer.parseInt(personData[2]);
        this.group = Integer.parseInt(personData[3]);
        for (var i = 4; i < personData.length; i++) {
            this.personGrades[i - 4] = Integer.parseInt(personData[i]);
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

    public Integer[] getPersonGrades() {
        Integer[] boxedValues = new Integer[personGrades.length];
        for (int i = 0; i < personGrades.length; i++) {
            boxedValues[i] = personGrades[i];
        }
        return boxedValues;
    }

    public double getStudentPerformance() {
        return IntStream.of(personGrades).average().getAsDouble();
    }


}
