package com.project.it_job.mapper.auth;

import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.auth.SaveUpdateUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
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

    public User saveUserMapper(Role role, Company company, SaveUpdateUserRequest saveUpdateUserRequest){
        return User.builder()
                .id(UUID.randomUUID().toString())
                .email(saveUpdateUserRequest.getEmail())
                .password(saveUpdateUserRequest.getPassword())
                .firstName(saveUpdateUserRequest.getFirstName())
                .lastName(saveUpdateUserRequest.getLastName())
                .avatar(saveUpdateUserRequest.getAvatar())
                .phone(saveUpdateUserRequest.getPhone())
                .gender(saveUpdateUserRequest.getGender())
                .education(saveUpdateUserRequest.getEducation())
                .address(saveUpdateUserRequest.getAddress())
                .linkweb(saveUpdateUserRequest.getLinkWeb())
                .birthDate(saveUpdateUserRequest.getBirthDate())
                .isFindJob(saveUpdateUserRequest.isFindJob())
                .groupSoftSkill(saveUpdateUserRequest.getGroupSoftSkill())
                .role(role)
                .company(company)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public User updateUserMapper(String idUser,Role role, Company company, SaveUpdateUserRequest saveUpdateUserRequest){
        return User.builder()
                .id(idUser)
                .email(saveUpdateUserRequest.getEmail())
                .password(saveUpdateUserRequest.getPassword())
                .firstName(saveUpdateUserRequest.getFirstName())
                .lastName(saveUpdateUserRequest.getLastName())
                .avatar(saveUpdateUserRequest.getAvatar())
                .phone(saveUpdateUserRequest.getPhone())
                .gender(saveUpdateUserRequest.getGender())
                .education(saveUpdateUserRequest.getEducation())
                .address(saveUpdateUserRequest.getAddress())
                .linkweb(saveUpdateUserRequest.getLinkWeb())
                .birthDate(saveUpdateUserRequest.getBirthDate())
                .isFindJob(saveUpdateUserRequest.isFindJob())
                .groupSoftSkill(saveUpdateUserRequest.getGroupSoftSkill())
                .role(role)
                .company(company)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
