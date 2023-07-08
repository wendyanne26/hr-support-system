package com.hrproject.demo.tutorial.dtos.requests;

import com.hrproject.demo.tutorial.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class HrRequest {
    @NotBlank(message = "first name is mandatory")
    private String firstName;
    private String nickName;
    @NotBlank(message = "first name is mandatory")
    private String lastName;
    @Email(message = "enter a valid email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Size(min = 11, max = 15, message = "digits should be between 11 and 15")
    @NotBlank(message = "phoneNumber must be present")
    private String phoneNumber;
    private String address;
    @Size(min = 8, max = 10, message = "password must be between 8 to 10 characters")
    @NotBlank(message = "password compulsory")
    private String password;
    private String position;
    private String reportTo;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date of birth format should be YYYY-MM-DD")
    private LocalDate dob;
}
