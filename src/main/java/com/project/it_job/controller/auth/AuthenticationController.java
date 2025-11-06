package com.project.it_job.controller.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @GetMapping
    public String getAuthentication(){
//        ==> tạo key cho jwt khi nào xong logic authentication thì hãy xóa
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        return secretString;

        return "";
    }
}
