package com.muyumi.RTKDataStructures.dataloaders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@NoArgsConstructor
@Component
@Getter
public class DataLoaderFromTextFile implements IDataLoader<String> {
    private static final String path = "students.csv";
    private String tableParams;

    @Override
    public ArrayList<String> readDataFromFile() {
        try (BufferedReader buff = new BufferedReader(new FileReader(path))) {
            ArrayList<String> fileData = new ArrayList<>();
            String line;
            tableParams = buff.readLine();
            while ((line = buff.readLine()) != null) {
                fileData.add(line);
            }
            return fileData;
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        throw new RuntimeException();
    }

    public String[] getSubjects() {
        String[] subjects = tableParams.split(",");
        return Arrays.copyOfRange(subjects, 4, subjects.length);
    }
}
