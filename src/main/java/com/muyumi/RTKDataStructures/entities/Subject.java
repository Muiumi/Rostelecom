package com.muyumi.RTKDataStructures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject_name", nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "subjects")
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

}
