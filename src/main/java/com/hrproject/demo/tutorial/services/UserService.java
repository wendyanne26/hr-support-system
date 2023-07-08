package com.hrproject.demo.tutorial.services;

import com.hrproject.demo.tutorial.dtos.requests.AdminRequest;
import com.hrproject.demo.tutorial.dtos.response.AdminResponse;
import com.hrproject.demo.tutorial.exceptions.EmployeeExistsException;

public interface UserService {
    AdminResponse register(AdminRequest adminRequest) throws EmployeeExistsException;
    boolean isValidDateOfBirth(String dateOfBirth);
}
