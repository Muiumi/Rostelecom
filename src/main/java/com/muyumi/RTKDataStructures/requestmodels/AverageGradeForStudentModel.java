package com.muyumi.RTKDataStructures.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AverageGradeForStudentModel {
    private String first_name;
    private String surname;
    private double average_grade;
}
