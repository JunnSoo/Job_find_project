package com.project.it_job.exception;

import com.project.it_job.exception.auth.*;
import com.project.it_job.exception.common.*;
import com.project.it_job.exception.file.FileExceptionHandler;
import com.project.it_job.exception.security.UnauthorizedDeleteExceptionHandler;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.util.security.CookieHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

        private final CookieHelper cookieHelper;

        @ExceptionHandler(NotFoundIdExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleNotFoundIdExceptionHandler(
                        NotFoundIdExceptionHandler ex, HttpServletRequest request) {
                // Log đầy đủ chi tiết cho developer
                log.error("NotFoundIdExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                // Trả về message user-friendly cho FE
                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Không tìm thấy dữ liệu yêu cầu";
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(BaseResponse.error(userMessage, HttpStatus.NOT_FOUND));
        }

        @ExceptionHandler(FileExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleFileException(
                        FileExceptionHandler ex, HttpServletRequest request) {
                log.error("FileExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Lỗi xử lý file. Vui lòng thử lại!";
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(BaseResponse.error(userMessage, HttpStatus.BAD_REQUEST));
        }

        // Không có quyền để xoá
        @ExceptionHandler(UnauthorizedDeleteExceptionHandler.class)
        public ResponseEntity<BaseResponse> hanldeUnauthorizedDeleteException(
                        UnauthorizedDeleteExceptionHandler ex, HttpServletRequest request) {
                log.error("UnauthorizedDeleteException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Bạn không có quyền để thực hiện thao tác này!";
                return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body(BaseResponse.error(userMessage, HttpStatus.FORBIDDEN));
        }

        // Lỗi email không được thay đổi
        @ExceptionHandler(EmailNotChangeExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleEmailNotChangeException(
                        EmailNotChangeExceptionHandler ex, HttpServletRequest request) {
                log.error("EmailNotChangeExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Email không được phép thay đổi!";
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(BaseResponse.error(userMessage, HttpStatus.BAD_REQUEST));
        }

        // Lỗi emai trùng khi thêm
        @ExceptionHandler(EmailAlreadyExistsExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleEmailAlreadyExisException(
                        EmailAlreadyExistsExceptionHandler ex, HttpServletRequest request) {
                log.error("EmailAlreadyExists: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Email này đã được sử dụng. Vui lòng chọn email khác!";
                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(BaseResponse.error(userMessage, HttpStatus.CONFLICT));
        }

        // Lỗi emai không tìm thấy
        @ExceptionHandler(EmailNotFoundExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleEmailNotFoundsException(
                        EmailNotFoundExceptionHandler ex, HttpServletRequest request) {
                log.error("EmailNotFoundExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Email không tồn tại trong hệ thống!";
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(BaseResponse.error(userMessage, HttpStatus.NOT_FOUND));
        }

        // Lỗi tham số
        @ExceptionHandler(ParamExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleParamException(
                        ParamExceptionHandler ex, HttpServletRequest request) {
                log.error("ParamExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Tham số không hợp lệ. Vui lòng kiểm tra lại!";
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(BaseResponse.error(userMessage, HttpStatus.BAD_REQUEST));
        }

        // Báo lỗi chung 500
        @ExceptionHandler(Exception.class)
        public ResponseEntity<BaseResponse> handleGeneralException(
                        Exception ex, HttpServletRequest request) {
                // Log đầy đủ chi tiết cho developer
                log.error("Unexpected Exception: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                // Trả về message generic cho FE (không tiết lộ chi tiết lỗi)
                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(BaseResponse.error("Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau!",
                                                HttpStatus.INTERNAL_SERVER_ERROR));
        }

        // Báo lỗi trùng dữ liệu hoặc đã có
        @ExceptionHandler(ConflictExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleConflictException(
                        ConflictExceptionHandler ex, HttpServletRequest request) {
                log.error("ConflictException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Dữ liệu đã tồn tại hoặc xung đột. Vui lòng kiểm tra lại!";
                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(BaseResponse.error(userMessage, HttpStatus.CONFLICT));
        }

        // LỖI VALIDATION
        // Hứng lỗi validation khi dùng @Valid @RequestBody
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<BaseResponse> handleValidationErrors(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors().forEach((e) -> errors.put(e.getField(), e.getDefaultMessage()));

                // Log đầy đủ chi tiết cho developer
                log.warn("Validation Error: {} | URI: {} | Method: {} | Errors: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                errors);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(BaseResponse.validationError(errors));
        }

        // AccessToken handle exception
        @ExceptionHandler(AccessTokenExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleAccessTokenException(
                        AccessTokenExceptionHandler ex, HttpServletRequest request) {
                log.error("AccessTokenExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Token không hợp lệ. Vui lòng đăng nhập lại!";
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(BaseResponse.error(userMessage, HttpStatus.UNAUTHORIZED));
        }

        // Wrong password
        @ExceptionHandler(WrongPasswordOrEmailExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleWrongPasswordException(
                        WrongPasswordOrEmailExceptionHandler ex, HttpServletRequest request) {
                log.error("WrongPasswordExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = "Tài khoản hoặc mật khẩu không chính xác!";
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(BaseResponse.error(userMessage, HttpStatus.UNAUTHORIZED));
        }

        // Block user
        @ExceptionHandler(BlockLoginUserExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleBlockUserException(
                        BlockLoginUserExceptionHandler ex, HttpServletRequest request) {
                log.error("BlockLoginUserExceptionHandler: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? "Bạn đã bị khóa đăng nhập tạm thời! " + ex.getMessage()
                                : "Bạn đã bị khóa đăng nhập tạm thời. Vui lòng thử lại sau!";
                return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body(BaseResponse.error(userMessage, HttpStatus.FORBIDDEN));
        }

        // RefreshToken handle exception
        @ExceptionHandler(RefreshTokenExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleRefreshTokenException(
                        RefreshTokenExceptionHandler ex, HttpServletRequest request) {
                log.error("RefreshTokenExceptionHanlder: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Token không hợp lệ. Vui lòng đăng nhập lại!";
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(BaseResponse.error(userMessage, HttpStatus.UNAUTHORIZED));
        }

        @ExceptionHandler(AlreadyLoggedInExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleAlreadyLoggedInException(
                        AlreadyLoggedInExceptionHandler ex, HttpServletRequest request) {
                log.error("AlreadyLoggedInException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Tài khoản này đã được đăng nhập từ thiết bị khác!";
                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(BaseResponse.error(userMessage, HttpStatus.CONFLICT));
        }

        @ExceptionHandler(ExpireTokenExceptionHandler.class)
        public ResponseEntity<?> handleRefreshTokenExpired(
                        ExpireTokenExceptionHandler ex, HttpServletRequest request, HttpServletResponse response) {
                cookieHelper.clearRefreshTokenCookie(response);

                log.error("ExpireTokenExceptionHanlder: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Phiên đăng nhập của bạn đã hết hạn. Vui lòng đăng nhập lại!";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(BaseResponse.error(userMessage, HttpStatus.UNAUTHORIZED));
        }

        // Lỗi gửi email
        @ExceptionHandler(SendEmailFailExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleSendEmailFailException(
                        SendEmailFailExceptionHandler ex, HttpServletRequest request) {
                log.error("SendEmailFailException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Không thể gửi email. Vui lòng thử lại sau!";
                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(BaseResponse.error(userMessage, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        // Xử lý lỗi không có quyền truy cập (Access Denied)
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<BaseResponse> handleAccessDeniedException(
                        AccessDeniedException ex, HttpServletRequest request) {
                log.warn("AccessDeniedException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage());

                String userMessage = "Bạn không có quyền!";
                return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body(BaseResponse.error(userMessage, HttpStatus.FORBIDDEN));
        }

        // OAuth2 Login Exception
        @ExceptionHandler(GoogleLoginExceptionHandler.class)
        public ResponseEntity<BaseResponse> handleOAuth2LoginException(
                GoogleLoginExceptionHandler ex, HttpServletRequest request) {
                log.error("OAuth2LoginException: {} | URI: {} | Method: {} | Message: {}",
                                ex.getClass().getSimpleName(),
                                request.getRequestURI(),
                                request.getMethod(),
                                ex.getMessage(),
                                ex);

                String userMessage = ex.getMessage() != null && !ex.getMessage().isBlank()
                                ? ex.getMessage()
                                : "Đăng nhập Google thất bại. Vui lòng thử lại.";
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(BaseResponse.error(userMessage, HttpStatus.UNAUTHORIZED));
        }
}
