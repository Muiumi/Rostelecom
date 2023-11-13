package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.exceptions.ClassroomNotFoundException;
import com.muyumi.RTKDataStructures.repositories.GradeRepository;
import com.muyumi.RTKDataStructures.requestmodels.AverageGradeForStudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GradeService implements EntityService<Grade> {

    @Autowired
    private GradeRepository gradeRepo;

    private int count = 0;
    private final ArrayList<Grade> grades = new ArrayList<>();


    @Override
    public void loadData(String[] dataRow) {
        for (var i = 0; i < dataRow.length; i++) {
            var grade = new Grade();
            grade.setGrade(Integer.parseInt(dataRow[i]));
            grade.setStudent(DBService.getCurrentStudent());
            grade.setSubject(DBService.getCurrentClassroom().getSubjects().get(i));
            grades.add(grade);
        }
        count++;
        if (count % DBService.getBATCH_SIZE() == 0) {
            saveData();
        }
    }

    @Override
    public void saveData() {
        count = 0;
        gradeRepo.saveAll(grades);
        gradeRepo.flush();
        grades.clear();
    }

    @Override
    public boolean entityIsPresent(Long id) {
        return gradeRepo.findById(id).isPresent();
    }

    @Override
    public Grade getOne(Long id) {
        return gradeRepo.findById(id).get();
    }

    public List<AverageGradeForStudentModel> getAverageGrades(int classroomId) throws ClassroomNotFoundException {
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
        if (studentsGrades.isEmpty()) {
            throw new ClassroomNotFoundException("Учебной группы с идентификатором " + classroomId + " не существует.");
        }
        return studentsGrades;
    }
}
