package com.example.userservice.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CompanyNameDto {
    private Long id;
    private String name;
}
