package com.project.it_job.controller.auth;

import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUserRequest;
import com.project.it_job.request.auth.UpdateUserRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.UserService;
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
