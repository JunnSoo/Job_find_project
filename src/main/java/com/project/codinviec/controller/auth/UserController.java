package com.project.codinviec.controller.auth;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.SaveUserRequest;
import com.project.codinviec.request.auth.UpdateUserRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(userService.getAllUsers(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(userService.getAllUsersPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser) {
        return ResponseEntity.ok(BaseResponse.success(userService.getUserById(idUser), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody SaveUserRequest saveUserRequest) {
        return ResponseEntity.ok(BaseResponse.success(userService.saveUser(saveUserRequest), "OK"));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable String idUser ,@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(BaseResponse.success(userService.updateUser(idUser, updateUserRequest), "OK"));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable String idUser) {
        return ResponseEntity.ok(BaseResponse.success(userService.deleteUser(idUser), "OK"));
    }

}
