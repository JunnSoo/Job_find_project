package com.project.codinviec.service.auth;

import com.project.codinviec.dto.auth.UserDTO;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.SaveUserRequest;
import com.project.codinviec.request.auth.UpdateUserRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    Page<UserDTO> getAllUsersPage(PageRequestCustom pageRequestCustom);
    UserDTO getUserById(String id);
    UserDTO saveUser(SaveUserRequest saveUserRequest);
    UserDTO updateUser(String idUser, UpdateUserRequest updateUserRequest);
    UserDTO deleteUser(String idUser);
}
