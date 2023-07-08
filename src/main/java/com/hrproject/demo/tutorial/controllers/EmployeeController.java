package com.hrproject.demo.tutorial.controllers;

import com.hrproject.demo.tutorial.dtos.requests.EmployeeRequest;
import com.hrproject.demo.tutorial.dtos.response.EmployeeResponse;
import com.hrproject.demo.tutorial.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @PutMapping("/profile")
    public ResponseEntity<EmployeeResponse> updateEmployeeProfile(@Valid @RequestBody EmployeeRequest employeeRequest){
    return new ResponseEntity<>(employeeService.updateEmployeeProfile(employeeRequest), HttpStatus.OK);
    }
    @PostMapping("uploadPic")
    public ResponseEntity<String> uploadProfilePic(@RequestParam("image")MultipartFile multipartFile){
        String imageUrl = employeeService.uploadImage(multipartFile);
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
    @PostMapping("uploadResume")
    public ResponseEntity<String> uploadResume(@RequestParam("resume") MultipartFile multipartFile){
        String resumeUrl = employeeService.uploadResume(multipartFile);
        return new ResponseEntity<>(resumeUrl, HttpStatus.OK);
    }
    public ResponseEntity<> viewProfile(){

    }

}
