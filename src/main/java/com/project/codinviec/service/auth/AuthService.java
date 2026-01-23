package com.project.codinviec.service.auth;

import com.project.codinviec.dto.auth.ProfileDTO;
import com.project.codinviec.dto.auth.RegisterDTO;
import com.project.codinviec.dto.auth.TokenDTO;
import com.project.codinviec.dto.auth.UserDTO;
import com.project.codinviec.request.ChangeSoftSkillRequest;
import com.project.codinviec.request.UpdateAvatarRequest;
import com.project.codinviec.request.UploadCvRequest;
import com.project.codinviec.request.auth.LoginRequest;
import com.project.codinviec.request.auth.GoogleUserRequest;
import com.project.codinviec.request.auth.RegisterRequest;
import com.project.codinviec.request.auth.UpdateProfileRequest;

public interface AuthService {
    TokenDTO login(LoginRequest request);

    TokenDTO refreshToken(String refreshToken);

    RegisterDTO register(RegisterRequest registerRequest);

    void logout(String refreshToken);

    TokenDTO googleLogin(GoogleUserRequest request);

    ProfileDTO getProfile(String userId);

    UserDTO updateProfile(String userId, UpdateProfileRequest request);

    UserDTO toggleIsFindJob(String userId);

    UserDTO changeSoftSkill(String userId, ChangeSoftSkillRequest changeSoftSkillRequest);

    UserDTO updateAvatar(String userId, UpdateAvatarRequest updateAvatarRequest);

    UserDTO uploadCv(String userId, UploadCvRequest uploadCvRequest);
}
