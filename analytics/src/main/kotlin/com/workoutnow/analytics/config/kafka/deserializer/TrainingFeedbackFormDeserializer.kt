package com.workoutnow.analytics.config.kafka.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.workoutnow.analytics.dto.TrainingFeedbackForm
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory

class TrainingFeedbackFormDeserializer : Deserializer<TrainingFeedbackForm> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): TrainingFeedbackForm? {
        log.info("Deserializing...")
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to TrainingFeedbackForm"),
                Charsets.UTF_8
            ), TrainingFeedbackForm::class.java
        )
    }

    override fun close() {}
}