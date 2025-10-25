package com.project.it_job.exception;

import com.project.it_job.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundIdExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleNotFoundIdException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Not Found Id!" );
        response.setData(null);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

//    LỖI VALIDATION
// Hứng lỗi validation khi dùng @Valid @RequestBody
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    BaseResponse response = new BaseResponse();
    response.setCode(HttpStatus.BAD_REQUEST.value());
    response.setMessage("validation failed!");
    response.setData(null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
}
}
