package com.workoutnow.analytics.repositories

import com.workoutnow.analytics.model.UserHealthData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserHealthDataRepository : MongoRepository<UserHealthData, String> {
    @Query("{ 'userId' : ?0 }")
    fun findAllByUserId(userId: String) :List<UserHealthData>
}