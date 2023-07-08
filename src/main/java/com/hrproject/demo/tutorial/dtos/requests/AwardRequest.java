package com.hrproject.demo.tutorial.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AwardRequest {
    @NotBlank(message = "title should not be blank")
    private String title;
    @NotBlank(message = "description should not be blank")
    private String description;
}
