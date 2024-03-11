package com.example.companyservice.dto;

import com.example.companyservice.model.Company;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CompanyNameDto {
    private Long id;
    private String name;

    public Company toCompany(){
        Company comp =new Company();
        comp.setId(id);
        comp.setName(name);
        return comp;
    }
    public CompanyNameDto fromCompany(Company company) {
        CompanyNameDto companyNameDto = new CompanyNameDto();
        companyNameDto.setId(company.getId());
        companyNameDto.setName(company.getName());
        return companyNameDto;
    }
}
