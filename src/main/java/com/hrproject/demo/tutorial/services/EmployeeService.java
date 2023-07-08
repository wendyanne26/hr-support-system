package com.hrproject.demo.tutorial.services;

import com.hrproject.demo.tutorial.dtos.requests.EmployeeRequest;
import com.hrproject.demo.tutorial.dtos.requests.HrRequest;
import com.hrproject.demo.tutorial.dtos.response.EmployeeResponse;
import com.hrproject.demo.tutorial.dtos.response.HrResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    HrResponse createHr(HrRequest hrRequest);

    EmployeeResponse updateEmployeeProfile(EmployeeRequest employeeRequest);

    String uploadImage(MultipartFile multipartFile);

    String uploadResume(MultipartFile multipartFile);
}
