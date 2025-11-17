package com.project.it_job.exception;

public class UnauthorizedDeleteExceptionHandler extends RuntimeException {
    public UnauthorizedDeleteExceptionHandler(String message) {
        super(message);
    }
}
