package com.workoutnow.analytics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class AnalyticsApplication

fun main(args: Array<String>) {
	runApplication<AnalyticsApplication>(*args)
}
