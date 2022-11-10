package com.workoutnow.analytics.config.kafka

import com.workoutnow.analytics.dto.ExperimentalExecutionForm
import com.workoutnow.analytics.dto.TrainingFeedbackForm
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val servers: String
) {
//    @Bean
//    fun consumerFactory(): ConsumerFactory<String?, Any?> {
//        val props: MutableMap<String, Any> = HashMap()
//        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
//        props[ConsumerConfig.GROUP_ID_CONFIG] = "ppr"
//        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
//        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = TrainingFeedbackFormDeserializer::class.java
//        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
//        return DefaultKafkaConsumerFactory(props)
//    }
//
    @Bean
    fun trainingFeedbackFormContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TrainingFeedbackForm>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, TrainingFeedbackForm>()
        factory.consumerFactory = trainingFeedbackFormConsumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.containerProperties.isSyncCommits = true
        return factory
    }

    private fun trainingFeedbackFormConsumerFactory(): ConsumerFactory<String?, TrainingFeedbackForm?> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        props[ConsumerConfig.GROUP_ID_CONFIG] = "training"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        return DefaultKafkaConsumerFactory(
            props, StringDeserializer(), JsonDeserializer<TrainingFeedbackForm>(TrainingFeedbackForm::class.java)
        )
    }




    @Bean
    fun experimentalExecutionFormContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ExperimentalExecutionForm>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ExperimentalExecutionForm>()
        factory.consumerFactory = experimentalExecutionFormConsumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.containerProperties.isSyncCommits = true
        return factory
    }

    private fun experimentalExecutionFormConsumerFactory(): ConsumerFactory<String?, ExperimentalExecutionForm?> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        props[ConsumerConfig.GROUP_ID_CONFIG] = "experimental"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        return DefaultKafkaConsumerFactory(
            props, StringDeserializer(), JsonDeserializer<ExperimentalExecutionForm>(ExperimentalExecutionForm::class.java)
        )
    }




//    @Bean
//    fun myMessageListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TrainingFeedbackForm>? {
//        val factory: ConcurrentKafkaListenerContainerFactory<String, TrainingFeedbackForm> =
//            ConcurrentKafkaListenerContainerFactory<String, TrainingFeedbackForm>()
//        factory.setConsumerFactory(myMessageFactory())
//        return factory
//    }
}