package com.hrproject.demo.tutorial.repositories;

import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmail(String username);


    Page<Employee> findAllByRole(Role role, Pageable pageable);
}
