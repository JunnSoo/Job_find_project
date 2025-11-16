package com.project.it_job.util;

import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.SendEmailFailException;
import com.project.it_job.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SendEmailHelpper {
    private final EmailService emailService;
    private final TimeHelpper timeHelpper;

    @Value("${email.link.register}")
    private String linkRegister;

    public void sendEmailRegister(User user){
        try {
            ClassPathResource resource = new ClassPathResource(linkRegister);
            String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            html = html.replace("{{fullName}}", user.getFirstName());
            html = html.replace("{{email}}", user.getEmail());
            html = html.replace("{{date}}", timeHelpper.parseLocalDateTimeToSimpleTime(user.getCreatedDate()));

            emailService.sendEmail(
                    user.getEmail(),
                    "Bạn đã đăng ký thành công!",
                    html
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new SendEmailFailException("Gửi email register thất bại");
        }
    }
}
