package com.github.stanyslavizmaylov.restaurant.util.exeption;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}
