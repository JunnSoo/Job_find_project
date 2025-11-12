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
                .body(BaseResponse.error("Không tìm thấy ", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(FileExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleFileException(FileExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Lỗi File!", HttpStatus.BAD_REQUEST));
    }

    // Không có quyền để xoá
    @ExceptionHandler(UnauthorizedDeleteException.class)
    public ResponseEntity<BaseResponse> hanldeUnauthorizedDeleteException(UnauthorizedDeleteException ex)
    {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(BaseResponse.error("Bạn không có quyền để thực hiện điều này!!", HttpStatus.BAD_REQUEST));
    }

//    Lỗi email không được thay đổi
    @ExceptionHandler(EmailNotChangeExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleEmailNotChangeException(EmailNotChangeExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Email không được thay đổi!", HttpStatus.BAD_REQUEST));
    }

//    Lỗi emai trùng khi thêm
    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<BaseResponse> handleEmailAlreadyExisException(EmailAlreadyExists ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Email đã tồn tại!", HttpStatus.BAD_REQUEST));
    }

    //    Lỗi emai không tìm thấy
    @ExceptionHandler(EmailNotFoundExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleEmailNotFoundsException(EmailNotFoundExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Email không được tìm thấy!", HttpStatus.BAD_REQUEST));
    }

//    Lỗi tham số
    @ExceptionHandler(ParamExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleParamException(ParamExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Lỗi truyền tham số!", HttpStatus.BAD_REQUEST));
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


    //   AccessToken handle exception
    @ExceptionHandler(AccessTokenExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleAccessTokenException(AccessTokenExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Token không hợp lệ!", HttpStatus.BAD_REQUEST));
    }


//    Wrong password
    @ExceptionHandler(WrongPasswordExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleWrongPasswordException(WrongPasswordExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Tài khoản hoặc mật khẩu không hợp lệ!", HttpStatus.BAD_REQUEST));
    }

//    Block user
//    Wrong password
    @ExceptionHandler(BlockLoginUserExceptionHandler.class)
    public ResponseEntity<BaseResponse> handleBlockUserException(BlockLoginUserExceptionHandler ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("Bạn đã bị khóa đăng nhập tạm thời! " + ex.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
