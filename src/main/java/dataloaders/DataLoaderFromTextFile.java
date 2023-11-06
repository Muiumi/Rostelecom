/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataloaders;

import com.muyumi.rtkdatagroupsproject.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Timkov Anton
 */
public class DataLoaderFromTextFile implements IDataLoader<Person> {

    private final String path;
    private static String tableParams;

    public DataLoaderFromTextFile(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<Person> loadData() {
        try (BufferedReader buff = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<Person> personsList = new ArrayList<>();
            tableParams = buff.readLine();
            while ((line = buff.readLine()) != null) {
                String[] personData = line.split(",");
                Person person = new Person(personData);
                personsList.add(person);

            }
            return personsList;
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

    public static String[] getSubjects() {
        String[] subjects = tableParams.split(",");
        return Arrays.copyOfRange(subjects,4,subjects.length);
    }
}
