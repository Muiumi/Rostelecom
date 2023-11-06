/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Timkov Anton
 */
public class DataGroup {

    private final IGroupCriterion criterion;
    private int CONTAINER_SIZE = 12;
    private List<Person>[] personsByGroupCriterion;

    public DataGroup(IGroupCriterion criterion) {
        this.criterion = criterion;
        this.personsByGroupCriterion = new ArrayList[CONTAINER_SIZE];
        for (int i = 0; i < personsByGroupCriterion.length; i++) {
            personsByGroupCriterion[i] = new ArrayList<>();
        }
    }

    public void addPerson(Person person) {
        int key = criterion.defineKey(person);
        try {
            personsByGroupCriterion[key - 1].add(person);
        } catch (ArrayIndexOutOfBoundsException ex) {
            extendArray();
            personsByGroupCriterion[key - 1].add(person);
        }

    }

    private void extendArray() {
        int previousContainerSize = CONTAINER_SIZE;
        CONTAINER_SIZE *= 1.5;
        personsByGroupCriterion = Arrays.copyOf(personsByGroupCriterion, CONTAINER_SIZE);
        for (int i = previousContainerSize; i < CONTAINER_SIZE; i++) {
            personsByGroupCriterion[i] = new ArrayList<>();
        }
    }

    public List<Person> getPersons(int keyForSearch) {
        return personsByGroupCriterion[keyForSearch - 1];
    }

    public int getGroupLength() {
        return personsByGroupCriterion.length;
    }

}
