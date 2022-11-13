package com.workoutnow.analytics.controller

import com.workoutnow.analytics.dto.UserAnalyticsDto
import com.workoutnow.analytics.service.AnalyticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}