package com.muyumi.RTKDataStructures.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NewStudentModel {
    private String first_name;
    private String surname;
    private int age;
    private Long classroom_num;
}
