package com.example.companyservice.service;

import com.example.companyservice.clients.UserServiceFeignClient;
import com.example.companyservice.dto.CompanyDto;
import com.example.companyservice.dto.CompanyNameDto;
import com.example.companyservice.dto.CompanyUserNameDto;
import com.example.companyservice.dto.UserNameDto;
import com.example.companyservice.model.Company;
import com.example.companyservice.repo.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional//(readOnly= true)
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final UserServiceFeignClient userServiceFeignClient;
    @Autowired
    private CompanyNameDto companyNameDto;
    private final CompanyKafkaProducerServiceImpl companyKafkaProducerService;

    public List<CompanyNameDto> getCompaniesName(){

        if(companyRepository.findAll().size()!=0) {
            List<CompanyNameDto> companyNameDtoList = companyRepository.findAll()
                    .stream()
                    .map(companyNameDto::fromCompany)
                    .toList();
            return companyNameDtoList;
        }
        return null;
    }
    public boolean existsById(long companyId){
        try {
            return  companyRepository.existsById(companyId);
        }
        catch (Exception exception){
            return false;
        }
    }
    public Long createCompany(CompanyDto companyDto){
        Boolean isExists = userServiceFeignClient.existById(companyDto.getDirector_id());
        if(!isExists){
            throw new EntityNotFoundException(
                    "Компания с идентификатором %s не существует".formatted(companyDto.getDirector_id())
            );
        }
        return companyRepository.save(companyDto.toCompany()).getId();
    }

    public List<CompanyUserNameDto> getAllCompanies(){
        List<UserNameDto> userNameDtoList=userServiceFeignClient.getUsersName();

        HashMap<Long,String> hashMap=new HashMap<>();
        for(int i=0; i<userNameDtoList.size();i++){
            hashMap.put(userNameDtoList.get(i).getId(),userNameDtoList.get(i).getName());
        }
        List<Company> companyList=companyRepository.findAll();
        if(companyList.size()!=0) {
            List<CompanyUserNameDto> companyUserNameDto=new ArrayList<>();
            for(int i=0; i<companyList.size();i++){
                companyUserNameDto.add(CompanyUserNameDto.fromUser(companyList.get(i),hashMap.get(companyList.get(i).getId())));
            }
            return companyUserNameDto;
        }
        return null;
    }

    @Transactional
    public void deleteCompany(Long id) {
        if(existsById(id)) {
            Company company = companyRepository.findById(id).get();
            company.setEnabled(false);
            companyRepository.save(company);
            companyKafkaProducerService.send("delete" + id, String.valueOf(id));
        }
    }
}
