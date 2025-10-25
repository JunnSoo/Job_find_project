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

    @ExceptionHandler(GetByIdExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleGetByIdException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setCode(400);
        response.setMessage("Data not found!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SaveExeptionHandler.class)
    public ResponseEntity<BaseResponse> handleSaveException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setCode(400);
        response.setMessage("Data save failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UpdateExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleUpdateException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setCode(400);
        response.setMessage("Data update failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DeleteExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleDeleteException(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setCode(400);
        response.setMessage("Data delete failed!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
