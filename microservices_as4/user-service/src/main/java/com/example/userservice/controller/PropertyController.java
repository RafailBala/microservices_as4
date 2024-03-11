package com.example.userservice.controller;

import com.example.userservice.config.Config;;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/properties")
public class PropertyController {

    private final Config config;

    @GetMapping
    public String getTestProperty() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("env/.env"));
        if (config.getSpringActiveProfiles().equals("dev"))
            config.setTestProperty(System.getProperty("DESCRIPTION_DEV"));
        else config.setTestProperty("User service");
        return config.getTestProperty();
    }
}
