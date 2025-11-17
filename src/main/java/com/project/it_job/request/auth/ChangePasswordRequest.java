package com.project.it_job.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Mật khẩu cũ không được để trống")
    private String oldPassword;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 12, max = 100, message = "Mật khẩu mới phải từ 12 đến 100 ký tự")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{12,}$",
            message = "Mật khẩu mới phải có ít nhất 12 ký tự, bao gồm: 1 chữ thường, 1 chữ hoa, 1 số và 1 ký tự đặc biệt (!@#$%^&*()_+-=[]{};':\"|,.<>/?)."
    )
    private String newPassword;
}
