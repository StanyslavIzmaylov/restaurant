package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.util.exeption.TimeRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(TimeRangeException.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorUUID = logErrorToNoSql(e);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("К сожалению, произошла ошибка. Обратитесь с этим идентификатором: " + errorUUID);
    }

    private String logErrorToNoSql(Exception e) {
        return UUID.randomUUID().toString();
    }

}
