package com.hrproject.demo.tutorial.dtos.requests;

import com.hrproject.demo.tutorial.entities.NextOfKin;
import com.hrproject.demo.tutorial.entities.entityUtil.Social;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class EmployeeRequest {
    @NotNull(message = "date of birth must not blank")
    @Past(message = "date of birth must be in the past")
    private LocalDate dateOfBirth;
    private String nickName;
    private String address;
    private NextOfKin nextOfKin;
    private Social social;
    private String nationality;
    private String maritalStatus;
}
