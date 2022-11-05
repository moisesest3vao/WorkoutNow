package com.workoutnow.general.models;

import com.workoutnow.general.dtos.TrainingForm;
import com.workoutnow.general.enums.ExerciseDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ExerciseDifficulty exerciseDifficulty;
    @ManyToMany
    private List<Exercise> exercises;

    public Training(TrainingForm form, List<Exercise> exercises) {
        this.exercises = exercises;
        this.name = form.getName();
        this.exerciseDifficulty = form.getExerciseDifficulty();
    }
}
