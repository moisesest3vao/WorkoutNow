package com.workoutnow.analytics.model

import com.workoutnow.analytics.dto.TrainingFeedbackForm
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class TrainingFeedbackData(
    @Id
    val id: ObjectId = ObjectId.get(),
    val trainingId: Long,
    val difficultyScale: Int,
    val wouldLikeToDoAgain: Boolean,
    val userId: String,
    val registerDate: Date = Date()
) {
    constructor(form: TrainingFeedbackForm, userId: String) : this (
        trainingId = form.trainingId,
        difficultyScale = form.difficultyScale,
        wouldLikeToDoAgain = form.wouldLikeToDoAgain,
        userId = userId,
    )
}