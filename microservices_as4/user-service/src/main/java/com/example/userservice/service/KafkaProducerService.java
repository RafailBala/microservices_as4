package com.example.userservice.service;

public interface KafkaProducerService {
    void send(String key, String message);
}
