/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtktask01;

import java.util.ArrayList;

/**
 *
 * @author timko
 */
public class ClassroomDataGroups {
    private final ArrayList<Person>[] classrooms = new ArrayList[12];

    public ClassroomDataGroups() {
        for (int i =0; i<classrooms.length;i++){
            classrooms[i] = new ArrayList<>();
        }
    }
 
    public void addPerson(Person person) {
        classrooms[person.getGroup()-1].add(person);
    }
    // возвращает студентов конкретной группы
    public ArrayList<Person> getPersons(int groupNum) {
        return classrooms[groupNum-1];
    }   
}
