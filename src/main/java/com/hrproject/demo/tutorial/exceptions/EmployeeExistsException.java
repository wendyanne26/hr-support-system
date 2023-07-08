package com.hrproject.demo.tutorial.exceptions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeExistsException extends RuntimeException {
    public EmployeeExistsException(String s) {
        super(s);
    }
}
