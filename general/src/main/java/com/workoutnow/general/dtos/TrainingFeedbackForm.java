package com.workoutnow.general.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingFeedbackForm {
    @NotNull
    private Long trainingId;
    @NotNull
    private Boolean wouldLikeToDoAgain;
    @Range(max = 10, min = 0)
    private Integer difficultyScale;
}
