package com.example.companyservice.clients;

import com.example.companyservice.dto.UserNameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "api-gateway",
        path = "/api/users"
)
public interface UserServiceFeignClient {
    @GetMapping("exists-by-id/{userId}")
    Boolean existById(@PathVariable("userId") Long userId);

    @GetMapping("/names")
    List<UserNameDto> getUsersName();
}
