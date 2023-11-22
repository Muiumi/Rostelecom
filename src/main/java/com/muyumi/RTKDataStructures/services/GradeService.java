package com.muyumi.RTKDataStructures.services;

import com.muyumi.RTKDataStructures.dto.AverageGradeForStudentDTO;
import com.muyumi.RTKDataStructures.entities.Grade;
import com.muyumi.RTKDataStructures.entities.Student;
import com.muyumi.RTKDataStructures.exceptions.ClassroomNotFoundException;
import com.muyumi.RTKDataStructures.repositories.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GradeService implements EntityService<Grade> {

    private final GradeRepository gradeRepo;

    @Override
    public void saveData(List<Grade> gradesList) {
        gradeRepo.saveAll(gradesList);
        gradeRepo.flush();
    }

    @Override
    public boolean entityIsPresent(Long id) {
        return gradeRepo.findById(id).isPresent();
    }

    @Override
    public Grade getOne(Long id) {
        return gradeRepo.findById(id).get();
    }

    public List<AverageGradeForStudentDTO> getAverageGrades(Long classroomId) throws ClassroomNotFoundException {
        var studentsGrades = new ArrayList<AverageGradeForStudentDTO>();
        List<Grade> grades = gradeRepo.findByStudentClassroomId(classroomId);
        Map<Student, List<Grade>> gradesForStudentMap = grades.stream().collect(Collectors.groupingBy(Grade::getStudent));
        for (Map.Entry<Student, List<Grade>> entry : gradesForStudentMap.entrySet()) {
            Student student = entry.getKey();
            List<Grade> studentGradesList = entry.getValue();
            double averageGrade = studentGradesList.stream().mapToInt(Grade::getGrade).average().orElse(0.0);
            var model = new AverageGradeForStudentDTO(student.getFirstName(), student.getSurname(), averageGrade);
            studentsGrades.add(model);
        }
        if (studentsGrades.isEmpty()) {
            throw new ClassroomNotFoundException("Учебной группы с идентификатором " + classroomId + " не существует.");
        }
        return studentsGrades;
    }
}
