package com.example.restaurant.util;

import java.time.LocalTime;

public class DateTimeUtil {
    private static LocalTime timeNow = LocalTime.now();

    public static LocalTime getTimeNow() {
        return timeNow;
    }

    public static void setTimeNow(LocalTime timeNow) {
        DateTimeUtil.timeNow = timeNow;
    }
}
