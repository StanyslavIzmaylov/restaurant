package com.example.restaurant.util.exeption;

public class IllegalRequestDataException extends RuntimeException{
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}