package com.workoutnow.analytics.listener

import com.workoutnow.analytics.model.ExperimentalExecutionForm
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class ExecutionListener {
    private val logger = LoggerFactory.getLogger(javaClass)

//    @KafkaListener(topics = ["user-health-data-collection-topic"], groupId = "persist-user-health-data")
//    fun listenGroupFoo(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
//        logger.info("Message received {}", consumerRecord)
//        ack.acknowledge()
//    }

    @KafkaListener(topics = ["user-health-data-collection-topic"], groupId = "persist-user-health-data")
    fun listenGroupFoo(experimentalExecutionForm: ExperimentalExecutionForm, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) userId: String, ack: Acknowledgment) {
        logger.info("Message received: userId=${userId} {}", experimentalExecutionForm)
        ack.acknowledge()
    }
}