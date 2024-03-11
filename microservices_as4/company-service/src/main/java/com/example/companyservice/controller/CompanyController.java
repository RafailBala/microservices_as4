package com.example.companyservice.controller;

import com.example.companyservice.dto.CompanyDto;
import com.example.companyservice.dto.CompanyNameDto;
import com.example.companyservice.service.CompanyService;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies/")
public class CompanyController {

    private final CompanyService companyService;

    //-------------------------ОШИБКИ-----------------------//

    @GetMapping(value = "names")
    public List<CompanyNameDto> getCompaniesName() throws IOException {
        return companyService.getCompaniesName();
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies() throws IOException {
        try {
            return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "exists-by-id/{companyId}")
    public ResponseEntity<?> existsById(@PathVariable(value = "companyId") long companyId) throws IOException {
        try {
            if (companyService.existsById(companyId)) {
                return new ResponseEntity<>(companyService.existsById(companyId), HttpStatus.OK);
            } else  return new ResponseEntity<>(companyService.existsById(companyId), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(companyId, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "create_company")
    public ResponseEntity<?> createUser(@RequestBody CompanyDto companyDto) throws IOException {
        try {
            return new ResponseEntity<>(companyService.createCompany(companyDto), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Пользователь не существует!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("delete company: " + id);
    }

    @ExceptionHandler({EntityNotFoundException.class, FeignException.class})
    public ResponseEntity<?> handlerEntityNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}