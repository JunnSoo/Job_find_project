package com.project.it_job.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String gender;
    private String education;
    private String address;
    private String websiteLink;
    private LocalDateTime birthDate;
    private boolean isFindJob;
    private String groupSoftSkill;
    private String companyId;
    private String roleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
