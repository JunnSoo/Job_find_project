package com.project.it_job.exception.common;

public class ConflictExceptionHandler extends RuntimeException {
    public ConflictExceptionHandler(String message) {
        super(message);
    }
}
