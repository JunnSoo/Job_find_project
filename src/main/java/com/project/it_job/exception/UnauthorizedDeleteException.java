package com.project.it_job.exception;

public class UnauthorizedDeleteException extends RuntimeException {
    public UnauthorizedDeleteException(String message) {
        super(message);
    }
}
