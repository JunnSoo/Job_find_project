package com.project.codinviec.exception.security;

public class UnauthorizedDeleteExceptionHandler extends RuntimeException {
    public UnauthorizedDeleteExceptionHandler(String message) {
        super(message);
    }
}
