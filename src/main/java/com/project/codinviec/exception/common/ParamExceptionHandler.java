package com.project.codinviec.exception.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamExceptionHandler extends RuntimeException {
    private String message;
}
