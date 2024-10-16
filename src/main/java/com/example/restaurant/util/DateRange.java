package com.example.restaurant.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateRange {
    private final LocalTime timeStop = LocalTime.of(11, 0, 0);
    private final LocalTime timeStart = LocalTime.of(0, 0, 0);

    public LocalTime getTimeStop() {
        return timeStop;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }
}
