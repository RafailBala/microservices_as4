package com.example.companyservice.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserNameDto {
    private Long id;
    private String name;
}
