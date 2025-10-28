package com.project.it_job.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUpdateUserRequest {
    @NotNull(message = "email must be not null")
    @NotEmpty(message = "email must be not empty")
    @Email(message = "email is not valid")
    private String email;

    @NotNull(message = "password must be not null")
    @NotEmpty(message = "password must be not empty")
    private String password;

    @NotNull(message = "firstName must be not null")
    @NotEmpty(message = "firstName must be not empty")
    private String firstName;

    @NotNull(message = "lastName must be not null")
    private String lastName;

    @NotNull(message = "avatar must be not null")
    private String avatar;

    @NotNull(message = "phone must be not null")
    private String phone;

    @NotNull(message = "gender must be not null")
    private String gender;

    @NotNull(message = "education must be not null")
    private String education;

    @NotNull(message = "address must be not null")
    private String address;

    @NotNull(message = "linkWeb must be not null")
    private String linkWeb;

//    @NotNull(message = "birthDate must be not null")
    private LocalDateTime birthDate;

    @NotNull(message = "isFindJob must be not null")
    private boolean isFindJob;

    @NotNull(message = "groupSoftSkill must be not null")
    private String groupSoftSkill;

    @NotNull(message = "companyId must be not null")
    private String companyId;

    @NotNull(message = "roleId must be not null")
    private String roleId;
}
