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
public class PersonAgeDataGroups {
    private final ArrayList<Person>[] ageGroups = new ArrayList[17];

    public PersonAgeDataGroups() {
        for (int i =0; i<ageGroups.length;i++){
            ageGroups[i] = new ArrayList<>();
        }
    }

    public int getAgeGroupsSize() {
        return ageGroups.length;
    }
    

    // внутренние поля для хранения данных
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        ageGroups[person.getAge()-1].add(person);
        
    }
    // возвращает студентов указанного адреса
    public ArrayList <Person> getPersons(int age) {
        return ageGroups[age-1];
    }
}
