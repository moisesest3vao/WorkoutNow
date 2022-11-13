package com.workoutnow.analytics.dto

import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData

class UserAnalyticsDto(
    userHealthList: List<UserHealthData>,
    trainingFeedbackDataList: List<TrainingFeedbackData>
)
