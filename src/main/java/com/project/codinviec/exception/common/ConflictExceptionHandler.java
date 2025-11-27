package com.project.codinviec.exception.common;

public class ConflictExceptionHandler extends RuntimeException {
    public ConflictExceptionHandler(String message) {
        super(message);
    }
}
