package com.workoutnow.general.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class TrainingFeedbackForm {
    @NotNull
    private Long trainingId;
    @NotNull
    private Boolean wouldLikeToDoAgain;
    @Range(max = 10, min = 0)
    private Integer difficultyScale;
}
