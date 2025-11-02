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
public class SaveUserRequest {
    @NotNull(message = "email không được null")
    @NotEmpty(message = "email không được rỗng")
    @Email(message = "email không hợp lệ")
    private String email;
    @NotNull(message = "password không được null")
    @NotEmpty(message = "password không được rỗng")
    private String password;

    @NotNull(message = "firstName không được null")
    @NotEmpty(message = "firstName không được rỗng")
    private String firstName;

    @NotNull(message = "lastName không được null")
    private String lastName;

    @NotNull(message = "avatar không được null")
    private String avatar;

    @NotNull(message = "phone không được null")
    private String phone;

    @NotNull(message = "gender không được null")
    private String gender;

    @NotNull(message = "education không được null")
    private String education;

    @NotNull(message = "address không được null")
    private String address;

    @NotNull(message = "linkWeb không được null")
    private String linkWeb;

//    @NotNull(message = "birthDate must be not null")
    private LocalDateTime birthDate;

    @NotNull(message = "isFindJob không được null")
    private boolean isFindJob;

    @NotNull(message = "groupSoftSkill không được null")
    private String groupSoftSkill;

    @NotNull(message = "companyId không được null")
    private String companyId;

    @NotNull(message = "roleId không được null")
    private String roleId;
}
