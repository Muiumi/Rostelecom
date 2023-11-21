package com.muyumi.RTKDataStructures.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AverageGradeForStudentDTO {
    private String firstName;
    private String surname;
    private double averageGrade;
}
