package com.muyumi.RTKDataStructures.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AverageGradeForStudentModel {
    private String firstName;
    private String surname;
    private double averageGrade;
}
