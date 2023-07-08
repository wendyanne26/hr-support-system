package com.hrproject.demo.tutorial.controllers;

import com.hrproject.demo.tutorial.dtos.requests.AddPermissionRequest;
import com.hrproject.demo.tutorial.dtos.requests.AddRolesRequest;
import com.hrproject.demo.tutorial.dtos.requests.AdminRequest;
import com.hrproject.demo.tutorial.dtos.requests.HrRequest;
import com.hrproject.demo.tutorial.dtos.response.AdminResponse;
import com.hrproject.demo.tutorial.dtos.response.HrResponse;
import com.hrproject.demo.tutorial.services.EmployeeService;
import com.hrproject.demo.tutorial.services.RoleService;
import com.hrproject.demo.tutorial.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<AdminResponse> registerAdmin(@Valid @RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = userService.register(adminRequest);
        return new ResponseEntity<>(adminResponse, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-role")
    public ResponseEntity<?> addRoles(@RequestBody AddRolesRequest addRolesRequest){
        return new ResponseEntity<>(roleService.addRoles(addRolesRequest), HttpStatus.OK);
    }
    @PostMapping("/addPermission/{role_id}")
    public ResponseEntity<?> addPermissions(@RequestBody AddPermissionRequest addPermissionRequest, @PathVariable String role_id){
        return new ResponseEntity<>(roleService.addPermission(addPermissionRequest, role_id), HttpStatus.OK);
    }

    @PostMapping("/createHr")
    public ResponseEntity<HrResponse> createHr(@Valid @RequestBody HrRequest hrRequest){
       return new ResponseEntity<>(employeeService.createHr(hrRequest), HttpStatus.CREATED);
    }
}
