package com.hrproject.demo.tutorial.services;

import com.hrproject.demo.tutorial.dtos.requests.AddPermissionRequest;
import com.hrproject.demo.tutorial.dtos.requests.AddRolesRequest;
import com.hrproject.demo.tutorial.dtos.response.AddPermissionResponse;
import com.hrproject.demo.tutorial.dtos.response.AddRoleResponse;

public interface RoleService {
    AddRoleResponse addRoles(AddRolesRequest addRolesRequest);

    AddPermissionResponse addPermission(AddPermissionRequest addPermissionRequest, String roleId);
}
