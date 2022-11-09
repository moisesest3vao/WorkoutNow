package com.workoutnow.analytics.service

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import com.workoutnow.analytics.model.UserHealthData
import com.workoutnow.analytics.repositories.UserHealthDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnalyticsService @Autowired constructor(
    private val userHealthDataRepository: UserHealthDataRepository
) {
    fun save(experimentalExecutionForm: ExperimentalExecutionForm, userId: String){
        val userHealthData = this.userHealthDataRepository.save(UserHealthData(experimentalExecutionForm, userId))
    }


}