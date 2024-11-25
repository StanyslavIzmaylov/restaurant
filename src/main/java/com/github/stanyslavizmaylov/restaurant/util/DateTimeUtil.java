package com.github.stanyslavizmaylov.restaurant.util;

import com.github.stanyslavizmaylov.restaurant.util.exeption.TimeRangeException;

import java.time.LocalTime;

public class DateTimeUtil {
    private static LocalTime timeNow = LocalTime.now();
    private static final LocalTime timeStop = LocalTime.of(11, 0, 0);
    private static final LocalTime timeStart = LocalTime.MIN;

    public static void timeRange(LocalTime localTime) {
        if (!localTime.isBefore(timeStop) && localTime.isAfter(timeStart)) {
            throw new TimeRangeException("You can update vote from 00:00 to 11:00");
        }
    }

    public static LocalTime getTimeNow() {
        return timeNow;
    }

    public static void setTimeNow(LocalTime timeNow) {
        DateTimeUtil.timeNow = timeNow;
    }
}
