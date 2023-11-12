package com.muyumi.RTKDataStructures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "classrooms")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Classroom {

    @Id
    @Column(name = "year_of_study")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "classrooms_subjects",
            joinColumns = @JoinColumn(name = "year_of_study"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroom")
    private List<Student> studentList;


}
