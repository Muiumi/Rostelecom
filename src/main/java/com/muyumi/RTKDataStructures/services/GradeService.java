package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.repositories.GradeRepository;
import com.muyumi.RTKDataStructures.requestmodels.AverageGradeForStudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<AverageGradeForStudentModel> getAverageGrades(int classroomId) {
        List<AverageGradeForStudentModel> studentsGrades = new ArrayList<>();
        List<Grade> grades = gradeRepo.findByStudentClassroomId(classroomId);
        Map<Student, List<Grade>> gradesForStudentMap = grades.stream().collect(Collectors.groupingBy(Grade::getStudent));
        for (Map.Entry<Student, List<Grade>> entry : gradesForStudentMap.entrySet()) {
            Student student = entry.getKey();
            List<Grade> studentGradesList = entry.getValue();
            double averageGrade = studentGradesList.stream().mapToInt(Grade::getGrade).average().orElse(0.0);
            var model = new AverageGradeForStudentModel(student.getFirstName(), student.getSurname(), averageGrade);
            studentsGrades.add(model);
        }
        return studentsGrades;
    }

}
