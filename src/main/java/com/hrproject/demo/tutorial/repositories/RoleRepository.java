package com.hrproject.demo.tutorial.repositories;

import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findRoleById(String admin);

    Optional<Role> findRoleByEmail(String employee);
}
