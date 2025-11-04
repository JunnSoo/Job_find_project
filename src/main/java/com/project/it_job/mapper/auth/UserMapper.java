package com.project.it_job.mapper.auth;

import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.dto.auth.UserReviewDTO;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.auth.SaveUserRequest;
import com.project.it_job.request.auth.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .gender(user.getGender())
                .education(user.getEducation())
                .address(user.getAddress())
                .linkweb(user.getLinkweb())
                .birthDate(user.getBirthDate())
                .isFindJob(user.isFindJob())
                .groupSoftSkill(user.getGroupSoftSkill())
                .companyId(user.getCompany() != null ? user.getCompany().getId() : null)
                .roleId(user.getRole()  != null ? user.getRole().getId() : null)
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    public User saveUserMapper(Role role, Company company, SaveUserRequest saveUserRequest){
        return User.builder()
                .id(UUID.randomUUID().toString())
                .email(saveUserRequest.getEmail())
                .password(passwordEncoder.encode(saveUserRequest.getPassword()))
                .firstName(saveUserRequest.getFirstName())
                .lastName(saveUserRequest.getLastName())
                .avatar(saveUserRequest.getAvatar())
                .phone(saveUserRequest.getPhone())
                .gender(saveUserRequest.getGender())
                .education(saveUserRequest.getEducation())
                .address(saveUserRequest.getAddress())
                .linkweb(saveUserRequest.getLinkWeb())
                .birthDate(saveUserRequest.getBirthDate())
                .isFindJob(saveUserRequest.isFindJob())
                .groupSoftSkill(saveUserRequest.getGroupSoftSkill())
                .role(role)
                .company(company)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public User updateUserMapper(String idUser,Role role, Company company, UpdateUserRequest updateUserRequest){
        return User.builder()
                .id(idUser)
                .email(updateUserRequest.getEmail())
                .firstName(updateUserRequest.getFirstName())
                .lastName(updateUserRequest.getLastName())
                .avatar(updateUserRequest.getAvatar())
                .phone(updateUserRequest.getPhone())
                .gender(updateUserRequest.getGender())
                .education(updateUserRequest.getEducation())
                .address(updateUserRequest.getAddress())
                .linkweb(updateUserRequest.getLinkWeb())
                .birthDate(updateUserRequest.getBirthDate())
                .isFindJob(updateUserRequest.isFindJob())
                .groupSoftSkill(updateUserRequest.getGroupSoftSkill())
                .role(role)
                .company(company)
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public UserReviewDTO userToUserReviewDTO(User user){
        return UserReviewDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .build();
    }
}
