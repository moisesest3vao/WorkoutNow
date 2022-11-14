package com.workoutnow.analytics.service

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import com.workoutnow.analytics.dto.TrainingFeedbackForm
import com.workoutnow.analytics.dto.UserAnalyticsDto
import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData
import com.workoutnow.analytics.repositories.TrainingFeedbackDataRepository
import com.workoutnow.analytics.repositories.UserHealthDataRepository
import com.workoutnow.analytics.service.util.UserUtil
import lombok.extern.log4j.Log4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnalyticsService @Autowired constructor(
    private val userHealthDataRepository: UserHealthDataRepository,
    private val trainingFeedbackDataRepository: TrainingFeedbackDataRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun saveHealthData(experimentalExecutionForm: ExperimentalExecutionForm, userId: String){
        this.userHealthDataRepository.save(UserHealthData(experimentalExecutionForm, userId))
    }

    fun saveTrainingFeedback(trainingFeedbackForm: TrainingFeedbackForm, userId: String) {
        this.trainingFeedbackDataRepository.save(TrainingFeedbackData(trainingFeedbackForm, userId))
    }

    fun getCurrentUserAnalytics(): UserAnalyticsDto {
        val userId: String = UserUtil.currentUserId;
        val userHealthList: List<UserHealthData> = this.userHealthDataRepository.findAllByUserId(userId);
        val trainingFeedbackDataList: List<TrainingFeedbackData> = this.trainingFeedbackDataRepository.findByUserId(userId)
        log.warn(userHealthList.toString())
        log.warn(trainingFeedbackDataList.toString())
        return UserAnalyticsDto(userHealthList, trainingFeedbackDataList)
    }

    fun getTrainingFeedbacks(id: Long, pageable: Pageable): Page<TrainingFeedbackData> {
        return this.trainingFeedbackDataRepository.findByTrainingId(id, pageable)
    }


}