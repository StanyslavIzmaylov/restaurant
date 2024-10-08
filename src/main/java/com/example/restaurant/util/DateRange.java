package com.example.restaurant.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateRange {
    private final LocalDateTime timeStop = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0, 0));
    private final LocalDateTime timeStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));

    public LocalDateTime getTimeStop() {
        return timeStop;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }
}
