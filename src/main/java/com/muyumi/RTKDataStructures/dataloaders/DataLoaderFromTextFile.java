package com.muyumi.RTKDataStructures.dataloaders;

import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
@Getter
public class DataLoaderFromTextFile implements DataLoader<NewStudentDTO> {
    private static final String path = "students.csv";
    private String tableParams;

    @Override
    public List<NewStudentDTO> readDataFromFile() throws FileNotFoundException {
        try (var buff = new BufferedReader(new FileReader(path))) {
            var fileData = new ArrayList<NewStudentDTO>();
            String line;
            tableParams = buff.readLine();
            while ((line = buff.readLine()) != null) {
                var dataFromRow = line.split(",", 5);
                // Заполняем модель студента из строки
                var student = new NewStudentDTO();
                student.setSurname(dataFromRow[0]);
                student.setFirstName(dataFromRow[1]);
                student.setAge(Integer.parseInt(dataFromRow[2]));
                student.setClassroomNum(Long.parseLong(dataFromRow[3]));
                student.setGradesRow(dataFromRow[4]);
                fileData.add(student);
            }
            return fileData;
        } catch (IOException ex) {
            throw new FileNotFoundException("Файл students.csv отсутствует в корне проекта ");
        }
    }

    public String[] getSubjects() {
        String[] subjects = tableParams.split(",");
        return Arrays.copyOfRange(subjects, 4, subjects.length);
    }
}
