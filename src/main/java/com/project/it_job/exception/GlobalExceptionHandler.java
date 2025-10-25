package com.project.it_job.exception;

import com.project.it_job.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SaveExeptionHandler.class)
    public ResponseEntity<BaseResponse> handleSaveException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Data save failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UpdateExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleUpdateException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Data update failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DeleteExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleDeleteException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Data delete failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(FileExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleFileException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("File is error!");
        response.setData(null);
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
