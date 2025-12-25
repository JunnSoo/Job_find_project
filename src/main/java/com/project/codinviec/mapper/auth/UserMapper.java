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
                .isFindJob(user.getIsFindJob())
                .groupSoftSkill(user.getGroupSoftSkill())
                .companyId(user.getCompany() != null ? user.getCompany().getId() : null)
                .isBlock(user.getIsBlock())
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
                .isFindJob(user.getIsFindJob())
                .groupSoftSkill(user.getGroupSoftSkill())
                .company(
                        user.getCompany() == null || user.getCompany().getId() == null
                                ? "Chưa Vào Công Ty"
                                : user.getCompany().getName()
                )
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    public User saveUserMapper(Role role, Company company, SaveUserRequest saveUserRequest, String passwword){
        return User.builder()
                .email(saveUserRequest.getEmail())
                .password(passwordEncoder.encode(passwword))
                .firstName(saveUserRequest.getFirstName())
                .lastName(saveUserRequest.getLastName())
                .phone(saveUserRequest.getPhone())
                .gender(saveUserRequest.getGender())
                .education(saveUserRequest.getEducation())
                .address(saveUserRequest.getAddress())
                .websiteLink(saveUserRequest.getWebsiteLink())
                .birthDate(saveUserRequest.getBirthDate())
                .isFindJob(false)
                .isBlock(false)
                .role(role)
                .company(company)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public User updateUserMapper(String idUser,Role role, Company company, UpdateUserRequest updateUserRequest, User user){
        user.setId(idUser);
        user.setRole(role);
        user.setCompany(company);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPhone(updateUserRequest.getPhone());
        user.setGender(updateUserRequest.getGender());
        user.setEducation(updateUserRequest.getEducation());
        user.setAddress(updateUserRequest.getAddress());
        user.setWebsiteLink(updateUserRequest.getWebsiteLink());
        user.setBirthDate(updateUserRequest.getBirthDate());
        user.setRole(role);
        user.setCompany(company);
        user.setUpdatedDate(LocalDateTime.now());
        return user;
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
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setPhone(request.getPhone());
        existingUser.setGender(request.getGender().toLowerCase());
        existingUser.setEducation(request.getEducation());
        existingUser.setAddress(request.getAddress());
        existingUser.setWebsiteLink(request.getWebsiteLink());
        existingUser.setBirthDate(request.getBirthDate());
    }
}
