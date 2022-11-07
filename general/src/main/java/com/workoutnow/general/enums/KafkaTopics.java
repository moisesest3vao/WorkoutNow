package com.workoutnow.general.enums;

public enum KafkaTopics {
    USER_HEALTH_DATA_TOPIC("user-health-data-collection-topic"),
    EXPERIMENTAL_TRAINING_RESULT("experimental-training-result-topic");

    public final String topic;

    KafkaTopics(String topic){
        this.topic = topic;
    }
}
