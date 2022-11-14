package com.workoutnow.analytics.repositories

import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TrainingFeedbackDataRepository : MongoRepository<TrainingFeedbackData, String> {
    @Query("{ 'userId' : ?0 }")
    fun findByUserId(userId: String) :List<TrainingFeedbackData>
    fun findByTrainingId(id: Long, pageable: Pageable): Page<TrainingFeedbackData>
}