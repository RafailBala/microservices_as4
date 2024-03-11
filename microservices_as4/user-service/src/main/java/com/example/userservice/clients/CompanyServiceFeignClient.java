package com.example.userservice.clients;


import com.example.userservice.dto.CompanyNameDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "api-gateway",
        path = "/api/companies"
)

public interface CompanyServiceFeignClient {
    @Operation(
            summary = "Запрос - проверка существования компании по id",
            description = "Обращается к Company-service и по id проверяет, существует ли компания возвращая в качестве ответа boolean"
    )
    @GetMapping("exists-by-id/{companyId}")
    Boolean existById(@PathVariable("companyId") Long companyId);

    @Operation(
            summary = "Запрос названия компаний",
            description = "Обращается к Company-service и запрашивает List из названия компаний"
    )
    @GetMapping("/names")
    List<CompanyNameDto> getCompaniesName();
}

