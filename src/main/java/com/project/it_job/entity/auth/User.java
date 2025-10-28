package com.project.it_job.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String gender;
    private String education;
    private String address;
    private String linkweb;
    private LocalDateTime birthDate;
    private boolean isFindJob;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedPerson;
    private String groupSoftSkill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
