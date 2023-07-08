package com.hrproject.demo.tutorial.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AwardResponse {
    @NotBlank(message = "title is compulsory")
    private String title;
    @NotBlank(message = "description is compulsory")
    private String description;
    private int year;
}
