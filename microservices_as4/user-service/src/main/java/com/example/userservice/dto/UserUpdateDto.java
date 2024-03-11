package com.example.userservice.dto;

import com.example.userservice.model.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserUpdateDto {
    public Long id;
    private String name;
    private String email;
    private Long companyId;
    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setCompanyId(companyId);
        return user;
    }
    public UserUpdateDto fromUser(User user) {
        UserUpdateDto userDto = new UserUpdateDto();
        userDto.setId(userDto.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCompanyId(user.getCompanyId());
        return userDto;
    }
}

