package com.hrproject.demo.tutorial.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private String departmentName;
    @DBRef
    private Employee departmentLead;
    @DBRef
    private List<Employee> employeeList = new ArrayList<>();
    @DBRef
    private List<Team> teamList = new ArrayList<>();
}
