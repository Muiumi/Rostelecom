/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muyumi.rtkdatagroupsproject;

/**
 *
 * @author Timkov Anton
 */
public class CommandBuilder {

    private final StudentService service;

    public CommandBuilder(StudentService service) {
        this.service = service;
    }

    public void commandFactory(String inputCommand) {
        Command command = null;

        switch (inputCommand) {
            case "averageGrades":
                command = new AverageGradesForClassroomCommand(service);
                break;
            case "findPerfectStudent":
                command = new FindPerfectStudentsCommand(service);
                break;
            case "searchBySurname":
                command = new SearchBySurnameCommand(service);
                break;
            case "help":
                System.out.println("averageGrades - Вычисление средней оценки в старших классах (10 и 11) \n"
                        + "findPerfectStudent - Поиск всех отличников, старше 14 лет\n"
                        + "searchBySurname - Поиск ученика по фамилии\n"
                        + "exit - Выход из программы");
                break;
            case "exit":
                System.exit(0);

        }
        try {
            command.execute();
        } catch (NullPointerException e) {
            System.out.println("Введите существующую команду:");
        }

    }

}
