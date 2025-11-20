package com.project.it_job.exception.auth;

/**
 * Exception cho OAuth2 login errors
 */
public class GoogleLoginExceptionHandler extends RuntimeException {

    public GoogleLoginExceptionHandler(String message) {
        super(message);
    }

    public GoogleLoginExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
