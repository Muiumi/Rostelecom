/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.muyumi.rtkdatagroupsproject;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Timkov Anton
 */
public class RTKDataStructures {

    public static void main(String[] args) throws IOException {
        DataLoaderFromTextFile fileDataLoader = new DataLoaderFromTextFile("students.csv");
        StudentService service = new StudentService(fileDataLoader);
        CommandBuilder builder = new CommandBuilder(service);

        Scanner cmdScan = new Scanner(System.in, "windows-1251");
        System.out.println("Для получения списка всех команд введите help, для выхода из программы введите exit.\nВведите желаемую команду");
        while (cmdScan.hasNext()) {
            builder.commandFactory(cmdScan.nextLine());
        }
    }
}
