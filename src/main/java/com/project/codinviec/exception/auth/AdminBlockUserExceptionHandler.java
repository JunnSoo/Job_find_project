package com.project.codinviec.exception.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminBlockUserExceptionHandler extends RuntimeException {
    private String message;
}
