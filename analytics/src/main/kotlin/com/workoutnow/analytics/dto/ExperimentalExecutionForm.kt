package com.workoutnow.analytics.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class ExperimentalExecutionForm (
    @JsonProperty("weight")
    @NotNull
    val weight: Long,
    @JsonProperty("height")
    @NotNull
    val height: Long,
    @NotNull
    @JsonProperty("hasSportHistory")
    val hasSportHistory: Boolean
)
