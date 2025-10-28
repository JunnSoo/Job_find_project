package com.project.it_job.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotChangeExceptionHandler extends RuntimeException {
    private String message;
}
