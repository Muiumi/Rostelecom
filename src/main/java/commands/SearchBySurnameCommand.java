/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import com.muyumi.rtkdatagroupsproject.DataGroup;
import com.muyumi.rtkdatagroupsproject.IGroupCriterion;
import com.muyumi.rtkdatagroupsproject.Person;
import com.muyumi.rtkdatagroupsproject.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Timkov Anton
 */
public class SearchBySurnameCommand implements ICommand {

    private final StudentService service;

    public SearchBySurnameCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getDataGroup()[2] == null) {
            IGroupCriterion nameCriterion = person -> ((int) person.getSurname().charAt(0) - 1039);
            service.getDataGroup()[2] = new DataGroup(nameCriterion);
            service.loadDataInSelectedStructure(service.getDataGroup()[2]);

        }
        /*
         3) Поиск ученика по фамилии
         Будем осуществлять поиск в структуре, сгруппированной по первой букве, учитывая выбранный метод с проверкой первой буквы первого элемента каждого массива в худшем случае будет O(n),
         например в случае, если фамилия начинается на букву Я. Затем при любом раскладе при последовательной проверке элементов массива будет O(n)
        */
        System.out.println("--- Поиск человека по фамилии ---");
        Scanner sc = new Scanner(System.in);
        var foundPersons = new ArrayList<String>();

        System.out.println("Введите искомую фамилию:");
        try {
            if (sc.hasNextLine()) {
                String surname = sc.nextLine().toUpperCase();
                for (Person person : service.getDataGroup()[2].getPersons(surname.charAt(0) - 1039)) {
                    if (person.getSurname().toUpperCase().startsWith(surname)) {
                        foundPersons.add(person.getSurname() + " " + person.getFirstName());
                    }
                }
                if (!foundPersons.isEmpty()) {
                    System.out.println("Список найденных людей для искомой фамилии: " + foundPersons);
                } else {
                    System.out.println("Людей с такой фамилией найдено не было");
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Проверьте корректность ввода");
        }

    }

}
