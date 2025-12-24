package com.project.codinviec.controller.auth;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.PageRequestUser;
import com.project.codinviec.request.auth.SaveUserRequest;
import com.project.codinviec.request.auth.UpdateUserRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(PageRequestUser pageRequestUser) {
        if (pageRequestUser.getPageNumber() == 0 && pageRequestUser.getPageSize() == 0  && pageRequestUser.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(userService.getAllUsers(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(userService.getAllUsersPage(pageRequestUser), "OK"));
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

    @PutMapping("/block/{idUser}")
    public ResponseEntity<?> blockUser(@PathVariable String idUser) {
        return ResponseEntity.ok(BaseResponse.success(userService.blockUser(idUser), "OK"));
    }

    @PutMapping("/unblock/{idUser}")
    public ResponseEntity<?> unBlockUser(@PathVariable String idUser) {
        return ResponseEntity.ok(BaseResponse.success(userService.unblockUser(idUser), "OK"));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable String idUser) {
        return ResponseEntity.ok(BaseResponse.success(userService.deleteUser(idUser), "OK"));
    }

}
