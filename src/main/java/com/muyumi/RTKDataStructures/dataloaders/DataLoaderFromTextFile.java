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
    private ArrayList<String> fileData;

    @Override
    public void readDataFromFile() {
        try (BufferedReader buff = new BufferedReader(new FileReader(path))) {
            fileData = new ArrayList<>();
            String line;
            tableParams = buff.readLine();
            while ((line = buff.readLine()) != null) {
                fileData.add(line);

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    @Override
    public void checkDataSource (){
        if (fileData == null){
            readDataFromFile();
        }
    }

    public String[] getSubjects() {
        String[] subjects = tableParams.split(",");
        return Arrays.copyOfRange(subjects, 4, subjects.length);
    }
}
