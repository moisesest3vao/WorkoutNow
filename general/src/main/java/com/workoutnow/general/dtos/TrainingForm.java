package com.workoutnow.general.dtos;

import com.workoutnow.general.enums.ExerciseDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class TrainingForm {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private ExerciseDifficulty exerciseType;
    @NotNull
    private List<Long> exercisesId;
}
