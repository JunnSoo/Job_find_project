package com.project.codinviec.mapper.auth;

import com.project.codinviec.dto.auth.ProfileDTO;
import com.project.codinviec.dto.auth.UserDTO;
import com.project.codinviec.dto.auth.UserReviewDTO;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.entity.auth.Role;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.request.auth.SaveUserRequest;
import com.project.codinviec.request.auth.UpdateProfileRequest;
import com.project.codinviec.request.auth.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                .websiteLink(user.getWebsiteLink())
                .birthDate(user.getBirthDate())
                .isFindJob(user.isFindJob())
                .groupSoftSkill(user.getGroupSoftSkill())
                .companyId(user.getCompany() != null ? user.getCompany().getId() : null)
                .roleId(user.getRole()  != null ? user.getRole().getId() : null)
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    public ProfileDTO userToProfileDTO(User user) {
        return ProfileDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .gender(user.getGender())
                .education(user.getEducation())
                .address(user.getAddress())
                .websiteLink(user.getWebsiteLink())
                .birthDate(user.getBirthDate())
                .isFindJob(user.isFindJob())
                .groupSoftSkill(user.getGroupSoftSkill())
                .company(
                        user.getCompany() == null || user.getCompany().getId() == null
                                ? "Chưa Vào Công Ty"
                                : user.getCompany().getName()
                )
                .role(user.getRole().getRoleName())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    public User saveUserMapper(Role role, Company company, SaveUserRequest saveUserRequest){
        return User.builder()
                .email(saveUserRequest.getEmail())
                .password(passwordEncoder.encode(saveUserRequest.getPassword()))
                .firstName(saveUserRequest.getFirstName())
                .lastName(saveUserRequest.getLastName())
                .avatar(saveUserRequest.getAvatar())
                .phone(saveUserRequest.getPhone())
                .gender(saveUserRequest.getGender())
                .education(saveUserRequest.getEducation())
                .address(saveUserRequest.getAddress())
                .websiteLink(saveUserRequest.getWebsiteLink())
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
                .websiteLink(updateUserRequest.getWebsiteLink())
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

    public void updateProfileMapper(User existingUser, UpdateProfileRequest request) {
        if (request.getFirstName() != null) {
            existingUser.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            existingUser.setLastName(request.getLastName());
        }
        if (request.getAvatar() != null) {
            existingUser.setAvatar(request.getAvatar());
        }
        if (request.getPhone() != null) {
            existingUser.setPhone(request.getPhone());
        }
        if (request.getGender() != null) {
            existingUser.setGender(request.getGender());
        }
        if (request.getEducation() != null) {
            existingUser.setEducation(request.getEducation());
        }
        if (request.getAddress() != null) {
            existingUser.setAddress(request.getAddress());
        }
        if (request.getWebsiteLink() != null) {
            existingUser.setWebsiteLink(request.getWebsiteLink());
        }
        if (request.getBirthDate() != null) {
            existingUser.setBirthDate(request.getBirthDate());
        }
        if (request.getIsFindJob() != null) {
            existingUser.setFindJob(request.getIsFindJob());
        }
        if (request.getGroupSoftSkill() != null) {
            existingUser.setGroupSoftSkill(request.getGroupSoftSkill());
        }
        existingUser.setUpdatedDate(LocalDateTime.now());
    }
}
