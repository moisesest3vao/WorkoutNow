package com.workoutnow.analytics.model

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document
class UserHealthData(
    @Id
    val id: ObjectId = ObjectId.get(),
    val weight: Long,
    val height: Long,
    val hasSportHistory: Boolean,
    val userId: String,
    val registerDate: Date = Date()
) {
    constructor(form: ExperimentalExecutionForm, userId: String) : this(
        weight = form.weight,
        height = form.height,
        hasSportHistory = form.hasSportHistory,
        userId = userId
    )
}