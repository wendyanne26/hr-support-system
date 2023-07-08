package com.hrproject.demo.tutorial.exceptions;

public class AwardNotFoundException extends RuntimeException {
    public AwardNotFoundException(String s) {
        super(s);
    }
}
