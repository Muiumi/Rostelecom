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
public class PersonNameDataGroup {
    private String lastLetter = "А";
    private int currentGroup = 0;
    private final ArrayList<Person>[] firstLetterGroups = new ArrayList[33];

    public PersonNameDataGroup() {
        for (int i =0; i<firstLetterGroups.length;i++){
            firstLetterGroups[i] = new ArrayList<>();
        }
    }
    
    
    // внутренние поля для хранения данных
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(String firstLetter, Person person) {
        if (firstLetter.equals(lastLetter)){
            firstLetterGroups[currentGroup].add(person);
        } else {
            currentGroup+=1;
            lastLetter = firstLetter;
            firstLetterGroups[currentGroup].add(person); 
        }
    }
    // возвращает студентов с фамилией, начинающейся на указанную букву
    public ArrayList<Person> getPersons(String firstLetter) {
        for (ArrayList<Person> firstLetterGroup : firstLetterGroups) {
            if (firstLetterGroup.get(0).getName().startsWith(firstLetter)) {
                return firstLetterGroup;
            }  
        }
    return null;  
    }
}

