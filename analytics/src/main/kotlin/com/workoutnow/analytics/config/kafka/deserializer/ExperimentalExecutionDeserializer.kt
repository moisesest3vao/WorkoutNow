package com.workoutnow.analytics.config.kafka.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8

class ExperimentalExecutionDeserializer : Deserializer<ExperimentalExecutionForm> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): ExperimentalExecutionForm? {
        log.info("Deserializing...")
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to ExperimentalExecutionForm"), UTF_8
            ), ExperimentalExecutionForm::class.java
        )
    }

    override fun close() {}
}