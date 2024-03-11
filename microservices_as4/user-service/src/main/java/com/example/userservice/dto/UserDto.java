package com.example.userservice.dto;


import com.example.userservice.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class UserDto {
    Long id;
    String name;
    String email;
    String login;
    String password;
    Long companyId;
    boolean enabled;

    public User toUser() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .login(login)
                .password(password)
                .companyId(companyId)
                .enabled(enabled)
                .build();
    }

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .companyId(user.getCompanyId())
                .enabled(user.getEnabled())
                .build();
    }
}
