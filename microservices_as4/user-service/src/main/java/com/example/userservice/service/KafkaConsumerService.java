package com.example.userservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerService {
    void receive(ConsumerRecord<String,String> record);
}
