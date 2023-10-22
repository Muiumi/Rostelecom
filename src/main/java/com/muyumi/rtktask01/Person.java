/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtktask01;

/**
 *
 * @author timko
 */
public class Person {
    private String name;
    private int age,group,physics,mathematics,rus,literature,geometry,informatics;
    private double studentPerformance;

    public Person(String[] personData) {
        this.name = personData[0]+" "+ personData[1];
        this.age = Integer.parseInt(personData[2]);
        this.group =Integer.parseInt(personData[3]);
        this.physics =Integer.parseInt(personData[4]);
        this.mathematics =Integer.parseInt(personData[5]);
        this.rus = Integer.parseInt(personData[6]);
        this.literature = Integer.parseInt(personData[7]);
        this.geometry = Integer.parseInt(personData[8]);
        this.informatics = Integer.parseInt(personData[9]);
        this.studentPerformance = (physics + rus + mathematics + literature + geometry + informatics)/6d;
    }


    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public int getPhysics() {
        return physics;
    }

    public int getMathematics() {
        return mathematics;
    }

    public int getRus() {
        return rus;
    }

    public int getLiterature() {
        return literature;
    }

    public int getGeometry() {
        return geometry;
    }

    public int getInformatics() {
        return informatics;
    }

    public double getStudentPerformance() {
        return studentPerformance;
    }
    

}
