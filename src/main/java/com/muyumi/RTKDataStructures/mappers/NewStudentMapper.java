package com.muyumi.RTKDataStructures.mappers;

import com.muyumi.RTKDataStructures.dto.NewStudentDTO;
import com.muyumi.RTKDataStructures.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewStudentMapper {
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "age", target = "age")
    @Mapping(target = "classroom", ignore = true)
    Student toStudent(NewStudentDTO newStudentDTO);
}
