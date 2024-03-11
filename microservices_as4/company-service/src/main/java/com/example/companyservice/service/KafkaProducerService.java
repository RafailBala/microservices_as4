package com.example.companyservice.service;

public interface KafkaProducerService {
    void send(String key, String message);
}
