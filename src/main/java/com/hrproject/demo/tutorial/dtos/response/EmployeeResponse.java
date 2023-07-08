package com.hrproject.demo.tutorial.dtos.response;

import com.hrproject.demo.tutorial.entities.NextOfKin;
import com.hrproject.demo.tutorial.entities.entityUtil.Social;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class EmployeeResponse {
    private LocalDate birthDay;
    private String nickName;
    private String address;
    private NextOfKin nextOfKin;
    private Social social;
    private String maritalStatus;
    private String nationality;
}
