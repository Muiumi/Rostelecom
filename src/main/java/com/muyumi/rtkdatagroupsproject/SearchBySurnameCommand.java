/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Timkov Anton
 */
public class SearchBySurnameCommand implements Command {

    private final StudentService service;

    public SearchBySurnameCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getDataGroup()[2] == null) {
            GroupCriterion nameCriterion = person -> (Integer.valueOf(person.getName().charAt(0)) - 1039);
            service.getDataGroup()[2] = new DataGroup(nameCriterion);
            service.loadDataGroup(service.getDataGroup()[2]);

        }
        // 3) Поиск ученика по фамилии
        // Будем осуществлять поиск в структуре, сгруппированной по первой букве, учитывая выбранный метод с проверкой первой буквы первого элемента каждого массива в худшем случае будет O(n),
        // например в случае, если фамилия начинается на букву Я. Затем при любом раскладе при последовательной проверке элементов массива будет O(n)
        System.out.println("--- Поиск человека по фамилии ---");
        Scanner sc = new Scanner(System.in, "windows-1251");
        ArrayList foundPersons = new ArrayList();

        System.out.println("Введите искомую фамилию:");
        try {
            if (sc.hasNextLine()) {
                String surname = sc.nextLine().toUpperCase();
                for (Person person : service.getDataGroup()[2].getPersons(surname.charAt(0) - 1039)) {
                    if (person.getName().toUpperCase().startsWith(surname)) {
                        foundPersons.add(person.getName());
                    }
                }
                if (!foundPersons.isEmpty()) {
                    System.out.println("Список найденных людей для искомой фамилии: " + foundPersons);
                } else {
                    System.out.println("Людей с такой фамилией найдено не было");
                }
            }
        } catch (Exception ex) {
            System.out.println("Проверьте корректность ввода");
        }

    }

}
