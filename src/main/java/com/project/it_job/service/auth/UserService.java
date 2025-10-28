package com.project.it_job.service.auth;

import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUpdateUserRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    Page<UserDTO> getAllUsersPage(PageRequestCustom pageRequestCustom);
    UserDTO getUserById(String id);
    UserDTO saveUser(SaveUpdateUserRequest saveUpdateUserRequest);
    UserDTO updateUser(String idUser, SaveUpdateUserRequest saveUpdateUserRequest);
    UserDTO deleteUser(String idUser);
}
