package com.hrproject.demo.tutorial.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Applicant extends BaseEntity{
    private String employeeFirstName;
    private String applicationLetterUrl;
}
