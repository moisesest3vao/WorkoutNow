package com.workoutnow.analytics.repositories

import com.workoutnow.analytics.model.TrainingFeedbackData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingFeedbackDataRepository : MongoRepository<TrainingFeedbackData, String> {
}