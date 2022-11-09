package com.workoutnow.analytics.repositories

import com.workoutnow.analytics.model.UserHealthData
import org.springframework.data.mongodb.repository.MongoRepository

interface UserHealthDataRepository : MongoRepository<UserHealthData, String> {
}