package com.workoutnow.general.dtos;

import com.workoutnow.general.enums.ExerciseDifficulty;
import com.workoutnow.general.models.Training;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TrainingDto {
    @Null
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private ExerciseDifficulty exerciseType;
    @NotNull
    private List<ExerciseDto> exercises;

    public TrainingDto(Training entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.exerciseType = entity.getExerciseType();
        this.exercises = entity.getExercises().stream().map(ExerciseDto::new).collect(Collectors.toList());
    }
}
