package com.hrproject.demo.tutorial.dtos.response;

import com.hrproject.demo.tutorial.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class HrResponse {
    private String firstName;
    private String lastName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNumber;
    private String reportTo;
    private String position;
    private Role role;
    private LocalDate dob;
}
