package com.hrproject.demo.tutorial.dtos.response;

import com.hrproject.demo.tutorial.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddPermissionResponse {
    private Role role;
}
