package com.project.it_job.exception.auth;

public class ExpireTokenExceptionHandler extends RuntimeException {
    public ExpireTokenExceptionHandler(String message) {
        super(message);
    }
}
