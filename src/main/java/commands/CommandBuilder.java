/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import com.muyumi.rtkdatagroupsproject.StudentService;

/**
 * @author Timkov Anton
 */
public class CommandBuilder {

    private final StudentService service;

    public CommandBuilder(StudentService service) {
        this.service = service;
    }

    public void commandFactory(String inputCommand)  {
        ICommand command = null;

        switch (inputCommand) {
            case "AvgGrades":
                command = new AverageGradesForClassroomCommand(service);
                break;
            case "PerfectStudents":
                command = new FindPerfectStudentsCommand(service);
                break;
            case "SurnameSearch":
                command = new SearchBySurnameCommand(service);
                break;
            case "help":
                System.out.println("""
                        1) Основные команды:
                            exit - Выход из программы;
                            BuildDB - Заполнить базу данных PostgreSQL ( БД необходимо предварительно загрузить для работы с командами из пункта 3);
                        2) Команды для работы с пользовательскими структурами:
                            AvgGrades - Вычисление средней оценки в старших классах (10 и 11 и 12);
                            PerfectStudents - Поиск всех отличников, старше 14 лет;
                            SurnameSearch - Поиск студента по указанной фамилии;
                        3) Команды для работы с базой данных PostgreSQL:
                            SQLSurnameSearch - Поиск студента по указанной фамилии;
                            SQLAvgGrades - Вычисление средней оценки в старших классах (10 и 11 и 12);
                            SQLPerfectStudents - Поиск всех отличников, старше 14 лет;
                        """);
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
