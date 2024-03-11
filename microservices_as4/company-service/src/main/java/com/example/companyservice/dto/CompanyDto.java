package com.example.companyservice.dto;

import com.example.companyservice.model.Company;
import lombok.Data;

@Data
public class CompanyDto {

    private Long id;
    private String name;
    private String ogrn;
    private String description;
    private Long director_id;

    public Company toCompany(){
        Company comp =new Company();
        comp.setId(id);
        comp.setName(name);
        comp.setOgrn(ogrn);
        comp.setDescription(description);
        comp.setDirector_id(director_id);
        return comp;
    }
    public static CompanyDto fromCompany(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setOgrn(company.getOgrn());
        companyDto.setDescription(company.getDescription());
        companyDto.setDirector_id(company.getDirector_id());
        return companyDto;
    }
}
