package com.workoutnow.general.models;

import com.workoutnow.general.enums.ExerciseType;

import javax.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;
}
