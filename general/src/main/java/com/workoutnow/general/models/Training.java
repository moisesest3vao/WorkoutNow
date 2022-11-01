package com.workoutnow.general.models;

import com.workoutnow.general.enums.ExerciseDifficulty;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ExerciseDifficulty exerciseType;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "training_exercise_mapping",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id", referencedColumnName = "id")})
    @MapKey(name = "name")
    private Map<Long, Exercise> exercises  = new HashMap<Long, Exercise>();
}
