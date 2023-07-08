package com.hrproject.demo.tutorial.services;

import com.hrproject.demo.tutorial.dtos.requests.AuthenticationRequest;
import com.hrproject.demo.tutorial.dtos.response.AuthenticationResponse;

public interface TokenService {
    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
}
