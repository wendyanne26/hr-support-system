package com.hrproject.demo.tutorial.dtos.response;

import com.hrproject.demo.tutorial.entities.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
public class AdminResponse {
    private String firstName;
    private String lastName;
    private String nickName;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private Role role;
    private String position;
}
