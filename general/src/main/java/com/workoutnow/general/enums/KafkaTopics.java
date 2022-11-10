package com.workoutnow.general.enums;

public enum KafkaTopics {
    USER_HEALTH_DATA_TOPIC("user-health-data-collection-topic"),
    TRAINING_FEEDBACK_TOPIC("training-feedback-topic");

    public final String topic;

    KafkaTopics(String topic){
        this.topic = topic;
    }
}
