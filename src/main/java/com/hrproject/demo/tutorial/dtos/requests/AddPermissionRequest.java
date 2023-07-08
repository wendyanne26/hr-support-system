package com.hrproject.demo.tutorial.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddPermissionRequest {
    private String permission;
}
