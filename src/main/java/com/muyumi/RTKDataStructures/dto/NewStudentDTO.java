package com.muyumi.RTKDataStructures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewStudentDTO {
    @JsonProperty("first_name")
    private String firstName;
    private String surname;
    private int age;
    @JsonProperty("classroom_num")
    private Long classroomNum;
    @JsonProperty("grades_row")
    private String gradesRow;

}
