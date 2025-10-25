package com.project.it_job.response;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private Object data;

    public static BaseResponse success(Object data, String message) {
        return new BaseResponse(HttpStatus.OK.value(), message, data);
    }

    public static BaseResponse error(String message, HttpStatus status) {
        return new BaseResponse(status.value(), message, null);
    }

    public static BaseResponse validationError(Map<String, String> errors) {
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), "Dữ liệu không hợp lệ!", errors);
    }


}
