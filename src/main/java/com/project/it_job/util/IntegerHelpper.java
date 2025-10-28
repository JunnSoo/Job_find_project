package com.project.it_job.util;

import com.project.it_job.exception.ParamExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class IntegerHelpper {
    public int parseIntOrThrow(Object value, String messageName) {
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ParamExceptionHandler("Truyền " + messageName + " không hợp lệ!");
        }
    }
}
