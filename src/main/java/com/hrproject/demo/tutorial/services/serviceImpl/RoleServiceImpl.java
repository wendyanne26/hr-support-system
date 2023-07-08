package com.hrproject.demo.tutorial.services.serviceImpl;

import com.hrproject.demo.tutorial.dtos.requests.AddPermissionRequest;
import com.hrproject.demo.tutorial.dtos.requests.AddRolesRequest;
import com.hrproject.demo.tutorial.dtos.response.AddPermissionResponse;
import com.hrproject.demo.tutorial.dtos.response.AddRoleResponse;
import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Permission;
import com.hrproject.demo.tutorial.entities.Role;
import com.hrproject.demo.tutorial.repositories.EmployeeRepository;
import com.hrproject.demo.tutorial.repositories.RoleRepository;
import com.hrproject.demo.tutorial.securityconfig.utils.SecurityUtils;
import com.hrproject.demo.tutorial.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final SecurityUtils securityUtils;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    String EXCEPTION_RESPONSE = "logged in user does not have required authority to perform function";


    @Override
    public AddRoleResponse addRoles(AddRolesRequest addRolesRequest) {
        if(getEmployeeRole()){
            Role role = Role.builder()
                    .id(addRolesRequest.getId())
                    .name(addRolesRequest.getName())
                    .permissions(new ArrayList<>())
                    .build();
            Role savedRole = roleRepository.save(role);
            return AddRoleResponse.builder()
                    .role(savedRole)
                    .build();
        }
        throw new RuntimeException(EXCEPTION_RESPONSE);
    }

    @Override
    public AddPermissionResponse addPermission(AddPermissionRequest addPermissionRequest, String roleId) {
        if(getEmployeeRole()) {
            Role role = roleRepository.findRoleById(roleId).orElseThrow(() -> new RuntimeException("role not found"));

            Permission permission = new Permission();
            permission.setPermission(addPermissionRequest.getPermission().toString());
            role.getPermissions().add(permission);
            Role savedRole = roleRepository.save(role);
            return AddPermissionResponse.builder()
                    .role(savedRole)
                    .build();
        }
        throw new RuntimeException("Logged in user does not have required authorities to perform this action");
    }

    private boolean getEmployeeRole() {
        UserDetails userDetails = securityUtils.getCurrentUserDetails();
        Employee employee = getEmployee(userDetails.getUsername());
        Role role = employee.getRole();
        return role.getName().equalsIgnoreCase("ADMIN");
    }

    private Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }
}
