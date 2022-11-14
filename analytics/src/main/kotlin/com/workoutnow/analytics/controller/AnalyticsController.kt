package com.workoutnow.analytics.controller

import com.workoutnow.analytics.dto.UserAnalyticsDto
import com.workoutnow.analytics.model.TrainingFeedbackData
import com.workoutnow.analytics.service.AnalyticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/analytics")
class AnalyticsController @Autowired constructor(
    private val analyticsService: AnalyticsService
){
    @GetMapping("/me")
    fun getAnalyticsByUser() : ResponseEntity<*>{
        val userAnalyticsDto: UserAnalyticsDto = this.analyticsService.getCurrentUserAnalytics()
        return ResponseEntity.ok(userAnalyticsDto)
    }

    @GetMapping("/training/feedback/{id}")
    fun getTrainingFeedbacks(@PathVariable id:Long,
                             @RequestParam size: Int,
                             @RequestParam page: Int) : ResponseEntity<Page<TrainingFeedbackData>>{
        val pageable: Pageable = PageRequest.of(page, size)
        val trainingFeedbackData: Page<TrainingFeedbackData> = this.analyticsService.getTrainingFeedbacks(id, pageable)

        if(!trainingFeedbackData.isEmpty)
            return ResponseEntity.ok(trainingFeedbackData)
        return ResponseEntity.notFound().build()
    }
}