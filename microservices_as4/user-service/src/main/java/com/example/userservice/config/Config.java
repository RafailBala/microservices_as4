package com.example.userservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Config {
    @Value("${my.property.test}")
    private String testProperty;

    @Value("${spring.active.profile}")
    private String springActiveProfiles;
}