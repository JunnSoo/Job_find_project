package com.project.it_job.controller.auth;
import com.project.it_job.request.auth.ChangePasswordRequest;
import com.project.it_job.service.auth.ChangePasswordService;
import com.project.it_job.util.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;
    private final JWTTokenUtil jwtTokenUtil;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ChangePasswordRequest request
    ) {
        // Lấy email từ token
        String token = authHeader.replace("Bearer ", "");
        String email = jwtTokenUtil.getUsername(token);

        changePasswordService.changePassword(email, request);

        return ResponseEntity.ok("Đổi mật khẩu thành công!");
    }
}
