package com.workoutnow.analytics.service

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import com.workoutnow.analytics.dto.TrainingFeedbackForm
import com.workoutnow.analytics.dto.UserAnalyticsDto
import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData
import com.workoutnow.analytics.repositories.TrainingFeedbackDataRepository
import com.workoutnow.analytics.repositories.UserHealthDataRepository
import com.workoutnow.analytics.service.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnalyticsService @Autowired constructor(
    private val userHealthDataRepository: UserHealthDataRepository,
    private val trainingFeedbackDataRepository: TrainingFeedbackDataRepository
) {
    fun saveHealthData(experimentalExecutionForm: ExperimentalExecutionForm, userId: String){
        this.userHealthDataRepository.save(UserHealthData(experimentalExecutionForm, userId))
    }

    fun saveTrainingFeedback(trainingFeedbackForm: TrainingFeedbackForm, userId: String) {
        this.trainingFeedbackDataRepository.save(TrainingFeedbackData(trainingFeedbackForm, userId))
    }

    fun getCurrentUserAnalytics(): UserAnalyticsDto {
        val userId: String = UserUtil.currentUserId;
        val userHealthList: List<UserHealthData> = this.userHealthDataRepository.findAllByUserId(userId);
        val trainingFeedbackDataList: List<TrainingFeedbackData> =
            this.trainingFeedbackDataRepository.findAllByUserId(userId)

        return UserAnalyticsDto(userHealthList, trainingFeedbackDataList)
    }



}