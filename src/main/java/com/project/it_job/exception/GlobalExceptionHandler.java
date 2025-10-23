package com.project.it_job.exception;

import com.project.it_job.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
