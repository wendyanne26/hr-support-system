package com.hrproject.demo.tutorial.controllers;

import com.hrproject.demo.tutorial.dtos.requests.AuthenticationRequest;
import com.hrproject.demo.tutorial.dtos.response.AuthenticationResponse;
import com.hrproject.demo.tutorial.repositories.TokenRepository;
import com.hrproject.demo.tutorial.services.TokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@NonNull @RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<>(tokenService.authenticateUser(authenticationRequest), HttpStatus.ACCEPTED);
    }
}
