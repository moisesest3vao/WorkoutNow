package com.workoutnow.analytics.service

import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.model.UserHealthData
import com.workoutnow.analytics.repositories.TrainingFeedbackDataRepository
import com.workoutnow.analytics.repositories.UserHealthDataRepository
import com.workoutnow.analytics.service.util.UserUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AnalyticsServiceTest {
    private lateinit var analyticsService: AnalyticsService
    @Mock
    private lateinit var userHealthDataRepository: UserHealthDataRepository
    @Mock
    private lateinit var trainingFeedbackDataRepository: TrainingFeedbackDataRepository

    object MockitoHelper {
        fun <T> anyObject(): T {
            Mockito.any<T>()
            return uninitialized()
        }
        @Suppress("UNCHECKED_CAST")
        fun <T> uninitialized(): T = null as T
    }

    @BeforeEach
    fun beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.analyticsService = AnalyticsService(userHealthDataRepository, trainingFeedbackDataRepository)
    }

    @Test
    fun shouldGetAllUserCollectedData(){
        Mockito.`when`(userHealthDataRepository. findAllByUserId(MockitoHelper.anyObject()))
            .thenReturn(List<UserHealthData>(10))
        Mockito.`when`(trainingFeedbackDataRepository. findByUserId(MockitoHelper.anyObject()))
            .thenReturn(List<TrainingFeedbackData>(10))

        Mockito.mockStatic(UserUtil::class.java).use { utilities ->
            UserUtil.currentUserId = "idteste"

            analyticsService.getCurrentUserAnalytics()

            Mockito.verify(userHealthDataRepository).findAllByUserId(MockitoHelper.anyObject())
            Mockito.verify(trainingFeedbackDataRepository).findByUserId(MockitoHelper.anyObject())
        }
    }
    private fun <T> List(size: Int): List<T> {
        return ArrayList<T>(size);
    }
}
