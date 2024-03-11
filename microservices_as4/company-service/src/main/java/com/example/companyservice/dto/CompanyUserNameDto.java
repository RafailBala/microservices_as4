package com.example.companyservice.dto;


import com.example.companyservice.model.Company;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CompanyUserNameDto {
    public Long id;
    private String name;
    private String ogrn;
    private String description;
    private String userName;

    public static CompanyUserNameDto fromUser(Company company, String userName) {
        CompanyUserNameDto userDto = new CompanyUserNameDto();
        userDto.setId(company.getId());
        userDto.setName(company.getName());
        userDto.setOgrn(company.getOgrn());
        userDto.setDescription(company.getDescription());
        userDto.setUserName(userName);
        return userDto;
    }
}
