package com.github.stanyslavizmaylov.restaurant.util.exeption;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
