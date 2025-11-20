package com.project.it_job.exception.auth;

public class AlreadyLoggedInExceptionHandler extends RuntimeException {
    public AlreadyLoggedInExceptionHandler(String message) {
        super(message);
    }
}
