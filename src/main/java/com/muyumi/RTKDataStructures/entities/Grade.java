package com.muyumi.RTKDataStructures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "grades")
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    @Column(name = "grade_id")
    private Long id;

    private int grade;

    @ManyToOne
    @JoinColumn(name = "name")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
