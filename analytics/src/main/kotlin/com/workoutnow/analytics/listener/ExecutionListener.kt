package com.workoutnow.analytics.listener

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import com.workoutnow.analytics.service.AnalyticsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import javax.validation.Valid

@Component
class ExecutionListener @Autowired constructor(
    private val analyticsService: AnalyticsService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

//    @KafkaListener(topics = ["user-health-data-collection-topic"], groupId = "persist-user-health-data")
//    fun listenGroupFoo(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
//        logger.info("Message received {}", consumerRecord)
//        ack.acknowledge()
//    }

    @KafkaListener(topics = ["user-health-data-collection-topic"], groupId = "persist-user-health-data")
    fun listenToExperimentalExecutionForm(@Valid experimentalExecutionForm: ExperimentalExecutionForm,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY, required = true) userId: String, ack: Acknowledgment){
        logger.info("Message received: userId=${userId} {}", experimentalExecutionForm)
        this.analyticsService.save(experimentalExecutionForm, userId)
        ack.acknowledge()
    }
}