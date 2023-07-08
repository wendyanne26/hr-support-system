package com.hrproject.demo.tutorial.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String s) {
       super(s);
    }
}
