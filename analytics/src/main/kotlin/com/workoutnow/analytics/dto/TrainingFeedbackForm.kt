package com.workoutnow.analytics.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class TrainingFeedbackForm (
    @NotNull
    @JsonProperty("trainingId")
    val trainingId: String,
    @NotNull
    @JsonProperty("difficultyScale")
    val difficultyScale: Int,
    @NotNull
    @JsonProperty("wouldLikeToDoAgain")
    val wouldLikeToDoAgain: Boolean
)
