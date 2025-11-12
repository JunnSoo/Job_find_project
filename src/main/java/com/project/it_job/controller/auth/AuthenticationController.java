package com.project.it_job.controller.auth;

import com.project.it_job.request.auth.LoginRequest;
import com.project.it_job.request.auth.RegisterRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @GetMapping
    public String getAuthentication(){
//        ==> tạo key cho jwt khi nào xong logic authentication thì hãy xóa
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        return secretString;

        return "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(BaseResponse.success(authService.login(request), "OK"));

    }

    @PostMapping("/regsiter")
    public ResponseEntity<?> regsiter(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(BaseResponse.success(authService.register(registerRequest), "OK"));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String id) {
        authService.logout(id);
        return ResponseEntity.ok(BaseResponse.success(null,"OK"));
    }
}
