package com.example.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @NotNull
    @Column(name = "company_id")
    private Long companyId;
    public boolean getEnabled() {
        return enabled;
    }
}
