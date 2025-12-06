package com.project.codinviec.util.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeHelper {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Chuyển LocalDateTime → String
    public String parseLocalDateTimeToSimpleTime(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }

    // Lấy thời gian hiện tại + mili giây
    public LocalDateTime nowPlus(long millis) {
        return LocalDateTime.now().plusNanos(millis * 1_000_000);
    }

    // Lấy thời gian hiện tại + mili giây -> String format
    public String nowPlusToString(long millis) {
        return nowPlus(millis).format(FORMATTER);
    }
}
