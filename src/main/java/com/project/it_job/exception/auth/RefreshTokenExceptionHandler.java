package com.project.it_job.exception.auth;

public class RefreshTokenExceptionHandler extends RuntimeException {
    public RefreshTokenExceptionHandler(String message) {
        super(message);
    }
}
