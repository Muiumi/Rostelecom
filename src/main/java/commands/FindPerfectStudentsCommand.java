/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import com.muyumi.rtkdatagroupsproject.*;

/**
 * @author Timkov Anton
 */
public class FindPerfectStudentsCommand implements ICommand {

    private final StudentService service;

    public FindPerfectStudentsCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getDataGroup()[0] == null) {
            IGroupCriterion ageCriterion = Person::getAge;
            service.getDataGroup()[0] = new DataGroup(ageCriterion);
            service.loadDataInSelectedStructure(service.getDataGroup()[0]);
        }
        System.out.println("--- Поиск всех отличников, старше 14 лет ---");
        // Последовательно (O(n)) проверим успеваемость персон в группах, которые соответствуют условию (>14 лет)
        int perfectCounter = 0;
        for (int i = 15; i <= service.getDataGroup()[0].getGroupLength(); i++) {
            for (Person person : service.getDataGroup()[0].getPersons(i)) {
                if (person.getStudentPerformance() == 5.0) {
                    System.out.println(person.getSurname() + " " + person.getFirstName());
                    perfectCounter++;
                }
            }
        }
        System.out.println("Всего отличников, старше 14 лет: " + perfectCounter);

    }
}
