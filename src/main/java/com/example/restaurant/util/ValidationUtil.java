package com.example.restaurant.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ValidationUtil {
    private static final LocalDateTime dateTimeStop = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 00, 00));
    private static final LocalDateTime dateTimeStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(00, 00, 00));

    public static void timeRange(LocalDateTime localDateTime) {
        if (!localDateTime.isBefore(dateTimeStop) && localDateTime.isAfter(dateTimeStart)) {
            throw new NullPointerException();
        }
    }
}
