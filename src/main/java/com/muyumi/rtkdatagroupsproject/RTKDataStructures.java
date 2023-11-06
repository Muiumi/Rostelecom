/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.muyumi.rtkdatagroupsproject;

import commands.CommandBuilder;
import dataloaders.DataLoaderFromTextFile;
import dbservices.PostgreSQLService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Timkov Anton
 */
public class RTKDataStructures {

    public static void main(String[] args) throws SQLException {

        var fileDataLoader = new DataLoaderFromTextFile("students.csv");
        var service = new StudentService(fileDataLoader);
        var serviceSQL = new PostgreSQLService(args[0], args[1],fileDataLoader);
        var builder = new CommandBuilder(service,serviceSQL);

        Scanner cmdScan = new Scanner(System.in);
        System.out.println("Для получения списка всех команд введите help, для выхода из программы введите exit.\nВведите желаемую команду");
        while (cmdScan.hasNext()) {
            builder.commandFactory(cmdScan.nextLine());
        }
    }
}
