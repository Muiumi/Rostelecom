/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.muyumi.rtktask01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author timko
 */
public class RTKtask01 {

    public static void main(String[] args) throws IOException {
        ClassroomDataGroups cdg = new ClassroomDataGroups();
        PersonAgeDataGroups padg = new PersonAgeDataGroups();
        PersonNameDataGroup pndg = new PersonNameDataGroup();
        
        try (BufferedReader buff = new BufferedReader(new FileReader("students.csv"))) {
            String line;
            while ((line = buff.readLine()) != null) {
                String[] personData = line.split(",");
                try {
                    Person person = new Person(personData);
                    cdg.addPerson(person);
                    padg.addPerson(person);
                    pndg.addPerson(person.getName().substring(0,1), person);
                   
                    
                } catch (NumberFormatException ex){}  
            }
            // 1) Вычисление средней оценки в старших классах (10 и 11)
            // Для вычисления средней оценки в 10 и 11 классах, воспользуемся группировкой учеников по номеру класса, затем последовательн,т.е. со сложностью O(n), у каждой персоны указанного класса
            // достаём значение средней арифмитической его оценок
            double markSumm10 = 0,
                   markSumm11 = 0;
            for (Person  person: cdg.getPersons(10)){
                markSumm10 += person.getStudentPerformance();
            }
            System.out.println("Средняя оценка учеников 10 класса: " + markSumm10/cdg.getPersons(10).size());
            
            for (Person  person: cdg.getPersons(11)){
                markSumm11 += person.getStudentPerformance();
            }
            System.out.println("Средняя оценка учеников 11 класса: " + markSumm11/cdg.getPersons(11).size());
            
            // 2) Поиск всех отличников, старше 14 лет
            // Пословательно (O(n)) проверим успеваемость персон в группах, которые соответствуют условию (>14 лет)
            System.out.println("Отличники старше 14 лет:");
            int perfectCounter = 0;
            for (int i = 15; i<=padg.getAgeGroupsSize(); i++){
                for (Person  person: padg.getPersons(i)){
                    if (person.getStudentPerformance() == 5.0){
                        System.out.println(person.getName());
                        perfectCounter +=1;
                    }
                }
            }
            System.out.println("Всего отличников, старше 14 лет: " + perfectCounter);
            
            // 3) Поиск ученика по фамилии
            // Будем осуществлять поиск в структуре, сгруппированной по первой букве, учитывая выбранный метод с проверкой первой буквы первого элемента каждого массива в худшем случае будет O(n),
            // например в случае, если фамилия начинается на букву Я. Затем при любом раскладе при последовательной проверке элементов массива будет O(n)

            Scanner sc = new Scanner(System.in,"windows-1251");
            ArrayList foundPersons = new ArrayList();
            
            System.out.println("Введите искомую фамилию:");
            try{
            if (sc.hasNextLine()){
                String surname = sc.nextLine().toUpperCase();
                for (Person person: pndg.getPersons(surname.substring(0,1))){
                    if (person.getName().toUpperCase().startsWith(surname)){
                        foundPersons.add(person.getName());
                    }
                }
                if (!foundPersons.isEmpty()){
                    System.out.println("Список найденных людей для искомой фамилии: " + foundPersons);
                } else {
                    System.out.println("Людей с такой фамилией найдено не было");
                }
            }
            } catch (Exception ex){ 
                System.out.println("Проверьте корректность ввода");
            }
        }
    }
}
    


