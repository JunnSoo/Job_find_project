package com.project.it_job.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WrongPasswordExceptionHandler extends RuntimeException {
    private String message;
}
