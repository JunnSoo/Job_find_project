package com.project.codinviec.exception.auth;

public class AlreadyLoggedInExceptionHandler extends RuntimeException {
    public AlreadyLoggedInExceptionHandler(String message) {
        super(message);
    }
}
