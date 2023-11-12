package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class GradeService implements IService<String> {

    @Autowired
    private GradeRepository gradeRepo;

    private int count = 0;
    private final ArrayList<Grade> grades = new ArrayList<>();

    @Override
    public void loadData(String dataRow) {
        String[] gradesFromRow = Arrays.copyOfRange(dataRow.split(","), 4, dataRow.split(",").length);
        for (var i = 0; i < gradesFromRow.length; i++) {
            var grade = new Grade();
            grade.setGrade(Integer.parseInt(gradesFromRow[i]));
            grade.setStudent(DBService.getCurrentStudent());
            grade.setSubject(DBService.getCurrentClassroom().getSubjects().get(i));
            grades.add(grade);
        }
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            gradeRepo.saveAll(grades);
            gradeRepo.flush();
            grades.clear();
        }
    }
}
