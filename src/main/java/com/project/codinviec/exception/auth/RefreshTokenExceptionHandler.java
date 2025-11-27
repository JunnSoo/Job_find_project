package com.project.codinviec.exception.auth;

public class RefreshTokenExceptionHandler extends RuntimeException {
    public RefreshTokenExceptionHandler(String message) {
        super(message);
    }
}
