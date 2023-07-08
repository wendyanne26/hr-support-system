package com.hrproject.demo.tutorial.exceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String s) {
        super(s);
    }
}
