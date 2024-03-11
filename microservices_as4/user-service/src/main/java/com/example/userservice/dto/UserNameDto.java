package com.example.userservice.dto;

import com.example.userservice.model.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserNameDto {
    private Long id;
    private String name;

    public UserNameDto fromUser(User user) {
        UserNameDto userNameDto = new UserNameDto();
        userNameDto.setId(user.getId());
        userNameDto.setName(user.getName());
        return userNameDto;
    }
}
