/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import com.muyumi.rtkdatagroupsproject.*;

/**
 * @author Timkov Anton
 */
public class AverageGradesForClassroomCommand implements Command {

    private final StudentService service;

    public AverageGradesForClassroomCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        if (service.getDataGroup()[1] == null) {
            GroupCriterion classroomCriterion = person -> person.getGroup();
            service.getDataGroup()[1] = new DataGroup(classroomCriterion);
            service.loadDataGroup(service.getDataGroup()[1]);

        }
        System.out.println("--- Расчёт средней оценки в старших классах (10 и 11) ---");
/*
        Для вычисления средней оценки в 10 и 11 классах, воспользуемся группировкой учеников по номеру класса, затем последовательно, т.е. со сложностью O(n),
        у каждой персоны указанного класса достаём значение средней арифметической его оценок
*/
        double markSumm10 = 0,
                markSumm11 = 0;
        for (Person person : service.getDataGroup()[1].getPersons(10)) {
            markSumm10 += person.getStudentPerformance();
        }
        System.out.println("Средняя оценка учеников 10 класса: " + markSumm10 / service.getDataGroup()[1].getPersons(10).size());

        for (Person person : service.getDataGroup()[1].getPersons(11)) {
            markSumm11 += person.getStudentPerformance();
        }
        System.out.println("Средняя оценка учеников 11 класса: " + markSumm11 / service.getDataGroup()[1].getPersons(11).size());

    }

}
