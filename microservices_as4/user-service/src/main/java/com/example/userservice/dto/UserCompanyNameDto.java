package com.example.userservice.dto;


import com.example.userservice.model.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserCompanyNameDto {
    public Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private boolean enabled;
    private String companyName;


    public static UserCompanyNameDto fromUser(User user, String companyName) {
        UserCompanyNameDto userDto = new UserCompanyNameDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setLogin(user.getLogin());
        userDto.setEnabled(user.getEnabled());
        userDto.setCompanyName(companyName);
        return userDto;
    }
}
