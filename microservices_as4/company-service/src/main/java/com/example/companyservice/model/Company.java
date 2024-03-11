package com.example.companyservice.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "ogrn")
    private String ogrn;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "director_id")
    private Long director_id;

    @Column(name = "enabled")
    private boolean enabled;
}
