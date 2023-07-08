package com.hrproject.demo.tutorial.exceptions;

public class NoRoleFoundException extends RuntimeException {
    public NoRoleFoundException(String roleNotFound) {
        super(roleNotFound);
    }
}
