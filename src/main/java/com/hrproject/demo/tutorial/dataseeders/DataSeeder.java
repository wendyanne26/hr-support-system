package com.hrproject.demo.tutorial.dataseeders;

import com.hrproject.demo.tutorial.entities.Permission;
import com.hrproject.demo.tutorial.entities.Role;
import com.hrproject.demo.tutorial.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DataSeeder implements CommandLineRunner {
    private final RoleRepository rolesRepository;

    @Override
    public void run(String... args) throws Exception {
        //set list of permission for the Admin
        List<Permission> adminPermission = new ArrayList<>();
        adminPermission.add(new Permission(Permission.ADMIN_DELETE));
        adminPermission.add(new Permission(Permission.ADMIN_CREATE));
        adminPermission.add(new Permission(Permission.ADMIN_READ));
        adminPermission.add(new Permission(Permission.ADMIN_UPDATE));
        adminPermission.add(new Permission(Permission.HR_CREATE));
        adminPermission.add(new Permission(Permission.HR_DELETE));
        adminPermission.add(new Permission(Permission.HR_READ));
        adminPermission.add(new Permission(Permission.HR_UPDATE));

        Role adminRole = new Role();
        adminRole.setId("admin");
        adminRole.setName("ADMIN");
        adminRole.setPermissions(adminPermission);

        //set list of permissions for Hr
        List<Permission> hrPermission = new ArrayList<>();
        hrPermission.add(new Permission(Permission.HR_UPDATE));
        hrPermission.add(new Permission(Permission.HR_DELETE));
        hrPermission.add(new Permission(Permission.HR_UPDATE));
        hrPermission.add(new Permission(Permission.HR_READ));

        Role hrRole = new Role();
        hrRole.setId("hr");
        hrRole.setName("HR");
        hrRole.setPermissions(hrPermission);

        Role staffRole = new Role();
        staffRole.setId("staff");
        staffRole.setName("STAFF");
        staffRole.setPermissions(Collections.emptyList());

        //save all roles to the database
        rolesRepository.saveAll(Arrays.asList(adminRole, hrRole, staffRole));
    }
}
