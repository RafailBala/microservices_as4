package com.example.userservice.service;

import com.example.userservice.clients.CompanyServiceFeignClient;
import com.example.userservice.dto.*;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional//(readOnly= true)
public class UserService {

    private final UserRepository userRepository;
    private final CompanyServiceFeignClient companyServiceFeignClient;
    @Autowired
    private UserDto userDto;
    @Autowired
    private UserNameDto userNameDto;

    public List<UserNameDto> getUsersName(){
        if(userRepository.findAll().size()!=0) {
            return userRepository.findAll()
                    .stream()
                    .map(userNameDto::fromUser)
                    .toList();
        }
        return null;
    }
    public List<UserCompanyNameDto> getAllUsers(){
        List<CompanyNameDto> companyNameDtoList=companyServiceFeignClient.getCompaniesName();;
        HashMap<Long,String> hashMap=new HashMap<>();
        for(int i=0; i<companyNameDtoList.size();i++){
            hashMap.put(companyNameDtoList.get(i).getId(),companyNameDtoList.get(i).getName());
        }
        List<User> userList=userRepository.findAll();
        if(userList.size()!=0) {
            List<UserCompanyNameDto> userCompanyNameDtoList=new ArrayList<>();
            for(int i=0; i<userList.size();i++){
                userCompanyNameDtoList.add(UserCompanyNameDto.fromUser(userList.get(i),hashMap.get(userList.get(i).getCompanyId())));
            }
            return userCompanyNameDtoList;
        }
        return null;
    }

    public boolean changeUserStatus(Long id){
        User user= userRepository.findById(id).orElse(null);
        assert user != null;
        user.setEnabled(!user.getEnabled());
        userRepository.save(user);
        return user.getEnabled();
    }
    public Long createUser(UserDto userDto){
       Boolean isExists = companyServiceFeignClient.existById(userDto.getCompanyId());
       if(!isExists){
           throw new EntityNotFoundException(
                    "Компания с идентификатором %s не существует".formatted(userDto.getCompanyId())
          );
        }
        return userRepository.save(userDto.toUser()).getId();
    }

    public UserDto updateUser(UserUpdateDto userUpdateDto){
        Boolean isExists = companyServiceFeignClient.existById(userUpdateDto.getCompanyId());
        if(!isExists){
            throw new EntityNotFoundException(
                    "Компания с идентификатором %s не существует".formatted(userUpdateDto.getCompanyId())
            );
        }
        User user= userRepository.findById(userUpdateDto.getId()).orElseThrow();
        user.setEmail(userUpdateDto.getEmail());
        user.setName(userUpdateDto.getName());
        user.setCompanyId(userUpdateDto.getCompanyId());
        userRepository.save(user);

        return userDto.fromUser(user);
    }

    @GetMapping(value = "exists-by-id/{userId}")
    public Boolean existsById( @PathVariable(value="userId") long userId) throws IOException {
        try {
            User user =userRepository.findById(userId).orElseThrow();
            if(!user.getEnabled()) return false;
            return userRepository.existsById(userId);
        }
        catch (Exception exception){
            return false;
        }
    }
}
