package com.project.it_job.exception;

import com.project.it_job.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundIdExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleNotFoundIdExceptionHandler(NotFoundIdExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(FileExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleFileException(FileExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Lỗi File!", HttpStatus.BAD_REQUEST));
    }

    //Báo lỗi chung 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error("Lỗi hệ thống: Vui lòng thử lại sau", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    // Báo lỗi trùng dữ liệu hoặc đã có
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<BaseResponse> handleConflictException(ConflictException ex) {
        ex.printStackTrace();
        BaseResponse response = new BaseResponse(
                HttpStatus.CONFLICT.value(),
                "Yêu cầu không hợp lệ!",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    // LỖI VALIDATION
    // Hứng lỗi validation khi dùng @Valid @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
       Map<String,String> errors = new HashMap<>();

       ex.getBindingResult().getFieldErrors().forEach((e)-> errors.put(e.getField(), e.getDefaultMessage()));

       return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(BaseResponse.validationError(errors));
    }
}
