package com.project.codinviec.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Họ không được để trống")
    @Size(min = 1, max = 50, message = "Họ phải từ 1 đến 50 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 50, message = "Tên phải từ 1 đến 50 ký tự")
    private String lastName;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 12, max = 100, message = "Mật khẩu phải từ 12 đến 100 ký tự")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{12,}$",
            message = "Mật khẩu phải có ít nhất 12 ký tự, bao gồm: 1 chữ thường, 1 chữ hoa, 1 số và 1 ký tự đặc biệt (!@#$%^&*()_+-=[]{};':\"|,.<>/?)."
    )
    private String password;
}