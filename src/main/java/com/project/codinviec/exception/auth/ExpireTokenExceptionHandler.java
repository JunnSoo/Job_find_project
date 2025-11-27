package com.project.codinviec.exception.auth;

public class ExpireTokenExceptionHandler extends RuntimeException {
    public ExpireTokenExceptionHandler(String message) {
        super(message);
    }
}
