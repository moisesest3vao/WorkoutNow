package com.workoutnow.general.dtos;

import com.sun.istack.NotNull;
import com.workoutnow.general.enums.ExerciseType;
import com.workoutnow.general.models.Exercise;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class ExerciseDto {
    @Null
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String exampleLink;
    @NotNull
    private ExerciseType exerciseType;
    @NotNull
    private Long repetitions;

    public ExerciseDto(Exercise exercise) {
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.exampleLink = exercise.getExampleLink();
        this.exerciseType = exercise.getExerciseType();
        this.repetitions = exercise.getRepetitions();
    }
}
