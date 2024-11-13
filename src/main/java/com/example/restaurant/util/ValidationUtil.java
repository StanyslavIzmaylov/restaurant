package com.example.restaurant.util;

import com.example.restaurant.HasId;
import com.example.restaurant.util.exeption.IllegalRequestDataException;
import com.example.restaurant.util.exeption.NotFoundException;
import com.example.restaurant.util.exeption.TimeRangeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ValidationUtil {
    private static final LocalDateTime dateTimeStop = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 00, 00));
    private static final LocalDateTime dateTimeStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(00, 00, 00));

    public static void timeRange(LocalDateTime localDateTime) {
        if (!localDateTime.isBefore(dateTimeStop) && localDateTime.isAfter(dateTimeStart)) {
            throw new TimeRangeException("You can vote from 00:00 to 11:00");
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }
}
