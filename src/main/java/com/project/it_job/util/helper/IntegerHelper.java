package com.project.it_job.util.helper;

import com.project.it_job.exception.common.ParamExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class IntegerHelper {
    public int parseIntOrThrow(Object value, String messageName) {
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ParamExceptionHandler("Truyền " + messageName + " không hợp lệ!");
        }
    }
}
