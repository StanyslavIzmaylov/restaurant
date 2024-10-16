package com.example.restaurant.util;

import java.time.LocalTime;

public class ValidationUtil {
    private static final LocalTime timeStop = LocalTime.of(11, 00, 00);
    private static final LocalTime timeStart = LocalTime.of(00, 00, 00);
    public static void timeRange(LocalTime localTime){
         if (!localTime.isBefore(timeStop) && localTime.isAfter(timeStart)){
             throw new NullPointerException();
        }
    }
}
