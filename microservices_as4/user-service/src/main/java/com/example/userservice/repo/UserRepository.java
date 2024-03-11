package com.example.userservice.repo;

import com.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> getUsersByCompanyId(Long companyId);
}
