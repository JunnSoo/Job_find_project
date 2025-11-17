package com.project.it_job.exception;


public class AlreadyLoggedInExceptionHandler extends RuntimeException {
    public AlreadyLoggedInExceptionHandler(String message) {
        super(message);
    }
}
