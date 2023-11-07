/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.muyumi.rtkdatagroupsproject;

import commands.CommandBuilder;
import dataloaders.DataLoaderFromTextFile;

import java.util.Scanner;

/**
 * @author Timkov Anton
 */
public class RTKDataStructures {

    public static void main(String[] args){

        var fileDataLoader = new DataLoaderFromTextFile("students.csv");
        var service = new StudentService(fileDataLoader);
        var builder = new CommandBuilder(service);

        Scanner cmdScan = new Scanner(System.in);
        System.out.println("Для получения списка всех команд введите help, для выхода из программы введите exit.\nВведите желаемую команду");
        while (cmdScan.hasNext()) {
            builder.commandFactory(cmdScan.nextLine());
        }
    }
}
