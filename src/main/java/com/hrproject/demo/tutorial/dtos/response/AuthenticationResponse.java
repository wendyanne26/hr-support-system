package com.hrproject.demo.tutorial.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String token;
    private Date issuedAt;
    private Date expiredAt;
}
