package com.project.it_job.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được quá 100 ký tự")
    private String email;

    @NotBlank(message = "Họ không được để trống")
    @Size(max = 50, message = "Họ không được quá 50 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 50, message = "Tên không được quá 50 ký tự")
    private String lastName;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{12,}$",
            message = "Mật khẩu phải ít nhất 12 ký tự, chứa 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt"
    )
    private String password;


}