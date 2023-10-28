/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

/**
 *
 * @author Timkov Anton
 */
public class FindPerfectStudentsCommand implements Command {

    private final StudentService service;

    public FindPerfectStudentsCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getDataGroup()[0] == null) {
            GroupCriterion ageCriterion = person -> person.getAge();
            service.getDataGroup()[0] = new DataGroup(ageCriterion);
            service.loadDataGroup(service.getDataGroup()[0]);
        }
        System.out.println("--- Поиск всех отличников, старше 14 лет ---");
        // Пословательно (O(n)) проверим успеваемость персон в группах, которые соответствуют условию (>14 лет)
        int perfectCounter = 0;
        for (int i = 15; i <= service.getDataGroup()[0].getGroupLength(); i++) {
            for (Person person : service.getDataGroup()[0].getPersons(i)) {
                if (person.getStudentPerformance() == 5.0) {
                    System.out.println(person.getName());
                    perfectCounter += 1;
                }
            }
        }
        System.out.println("Всего отличников, старше 14 лет: " + perfectCounter);

    }
}
