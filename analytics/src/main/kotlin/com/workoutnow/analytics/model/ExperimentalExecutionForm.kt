package com.workoutnow.analytics.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ExperimentalExecutionForm (
    @JsonProperty("weight")
    val weight: Long,
    @JsonProperty("height")
    val height: Long,
    @JsonProperty("hasSportHistory")
    val hasSportHistory: Boolean
)
