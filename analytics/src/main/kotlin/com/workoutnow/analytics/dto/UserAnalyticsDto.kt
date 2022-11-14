package com.workoutnow.analytics.dto

import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData

data class UserAnalyticsDto(
    val userHealthList: List<UserHealthData>,
    val trainingFeedbackDataList: List<TrainingFeedbackData>
)
