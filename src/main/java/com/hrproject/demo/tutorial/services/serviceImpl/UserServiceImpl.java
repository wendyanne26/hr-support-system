package com.hrproject.demo.tutorial.services.serviceImpl;

import com.hrproject.demo.tutorial.dtos.requests.AdminRequest;
import com.hrproject.demo.tutorial.dtos.response.AdminResponse;
import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Role;
import com.hrproject.demo.tutorial.exceptions.EmployeeExistsException;
import com.hrproject.demo.tutorial.exceptions.InvalidDateException;
import com.hrproject.demo.tutorial.exceptions.NoRoleFoundException;
import com.hrproject.demo.tutorial.mapper.Mapper;
import com.hrproject.demo.tutorial.repositories.EmployeeRepository;
import com.hrproject.demo.tutorial.repositories.RoleRepository;
import com.hrproject.demo.tutorial.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    @Override
    public AdminResponse register(AdminRequest adminRequest){
      if(employeeRepository.findByEmail(adminRequest.getEmail()).isPresent()){
          throw new EmployeeExistsException("employee with "+ adminRequest.getEmail()+ " already exists");
      }
      if(!isValidDateOfBirth(adminRequest.getDateOfBirth())) {
          throw new InvalidDateException("Invalid: date of birth");
      }
        Optional<Role> adminRole = roleRepository.findRoleById("admin");
        if(adminRole.isPresent()){
            Employee employee = mapper.toEntity(adminRequest);
            employee.setRole(adminRole.get());
            Employee registeredEmployee = employeeRepository.save(employee);
            return mapper.toResponse(registeredEmployee);
        }else {
            throw new NoRoleFoundException("role not found");
        }
    }

    @Override
    public boolean isValidDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        LocalDate now = LocalDate.now();
        LocalDate oldestDate = now.minusYears(120);
        return dob.isBefore(now) && dob.isAfter(oldestDate);
    }


}
