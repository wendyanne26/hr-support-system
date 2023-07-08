package com.hrproject.demo.tutorial.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AdminRequest {
    @NotBlank(message = "first name must not be blank")
    @Size(min = 5, max = 26, message = "first name length must be between 5 and 26")
    private String firstName;
    private String nickName;
    @NotBlank(message = "last name characters must not be blank")
    @Size(min = 5, max = 26, message = "last name length must be between 5 and 26")
    private String lastName;
    @NotBlank(message = "invalid: password must not be blank")
    @Size(min = 5, max = 26, message = "password characters must be between 5 and 26")
    private String password;
    @NotBlank(message = "invalid: date must not be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date format must be 'yyyy-MM-dd'")
    private String dateOfBirth;
    @Email
    @NotBlank(message = "Invalid: Email must not be blank")
    private String email;
    @Pattern(regexp = "^\\d{10,12}$", message = "Phone number must be between 10 to 12 digits")
    private String phoneNumber;

}
