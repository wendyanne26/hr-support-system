package com.hrproject.demo.tutorial.mapper;

import com.hrproject.demo.tutorial.dtos.requests.AdminRequest;
import com.hrproject.demo.tutorial.dtos.response.AdminResponse;
import com.hrproject.demo.tutorial.dtos.response.AwardResponse;
import com.hrproject.demo.tutorial.dtos.response.EmployeeResponse;
import com.hrproject.demo.tutorial.dtos.response.HrResponse;
import com.hrproject.demo.tutorial.entities.Award;
import com.hrproject.demo.tutorial.entities.Employee;
import com.sun.mail.imap.protocol.BASE64MailboxEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class Mapper {
    private final PasswordEncoder passwordEncoder;

    public Employee toEntity(AdminRequest adminRequest){
        return Employee.builder()
                .birthDate(LocalDate.parse(adminRequest.getDateOfBirth()))
                .phoneNo(adminRequest.getPhoneNumber())
                .position("Manager")
                .firstName(adminRequest.getFirstName())
                .lastName(adminRequest.getLastName())
                .nickName(adminRequest.getNickName())
                .password(passwordEncoder.encode(adminRequest.getPassword()))
                .email(adminRequest.getEmail())
                .build();
    }

    public AdminResponse toResponse(Employee employee) {
        return AdminResponse.builder()
                .firstName(employee.getFirstName())
                .nickName(employee.getNickName())
                .lastName(employee.getLastName())
                .position(employee.getPosition())
                .dateOfBirth(employee.getBirthDate().toString())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNo())
                .role(employee.getRole())
                .build();
    }

    public HrResponse toHrResponse(Employee employee) {
        return HrResponse .builder()
                .firstName(employee.getFirstName())
                .nickName(employee.getNickName())
                .lastName(employee.getLastName())
                .role(employee.getRole())
                .phoneNumber(employee.getPhoneNo())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .build();
    }

    public AwardResponse toAwardResponse(Award award) {
        return AwardResponse.builder()
                .description(award.getDescription())
                .title(award.getTitle())
                .year(award.getYear())
                .build();
    }

    public AwardResponse toResponse(Award award) {
        return AwardResponse.builder()
                .description(award.getDescription())
                .title(award.getTitle())

                .build();
    }

    public EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .address(employee.getAddress())
                .birthDay(employee.getBirthDate())
                .nationality(employee.getNationality())
                .maritalStatus(employee.getMaritalStatus())
                .nextOfKin(employee.getNextOfKin())
                .social(employee.getSocial())
                .nickName(employee.getNickName())
                .build();
    }
}
