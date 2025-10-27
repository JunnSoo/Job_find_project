package com.project.it_job.exception;


public class NotFoundIdExceptionHandler extends RuntimeException {
    public NotFoundIdExceptionHandler(String message) {
        super(message);
    }

}
