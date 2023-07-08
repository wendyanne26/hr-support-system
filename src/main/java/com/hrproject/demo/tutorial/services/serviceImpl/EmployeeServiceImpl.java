package com.hrproject.demo.tutorial.services.serviceImpl;

import com.cloudinary.Cloudinary;
import com.hrproject.demo.tutorial.dtos.requests.EmployeeRequest;
import com.hrproject.demo.tutorial.dtos.requests.HrRequest;
import com.hrproject.demo.tutorial.dtos.response.EmployeeResponse;
import com.hrproject.demo.tutorial.dtos.response.HrResponse;
import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Role;
import com.hrproject.demo.tutorial.exceptions.EmailNotFoundException;
import com.hrproject.demo.tutorial.exceptions.EmployeeExistsException;
import com.hrproject.demo.tutorial.mapper.Mapper;
import com.hrproject.demo.tutorial.repositories.EmployeeRepository;
import com.hrproject.demo.tutorial.repositories.RoleRepository;
import com.hrproject.demo.tutorial.securityconfig.utils.SecurityUtils;
import com.hrproject.demo.tutorial.services.EmployeeService;
import com.sun.mail.imap.protocol.BASE64MailboxEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Mapper mapper;
    private final SecurityUtils securityUtils;
    private final Cloudinary cloudinary;

    @Override
    public HrResponse createHr(HrRequest hrRequest) {
    if(employeeRepository.findByEmail(hrRequest.getEmail()).isPresent()){
        throw new EmployeeExistsException("employee already exists");
    }
        Optional<Role> hrRole = roleRepository.findRoleById("hr");
    Employee employee = new Employee();
    BeanUtils.copyProperties(hrRequest, employee);
    employee.setPassword(passwordEncoder.encode(hrRequest.getPassword()));
    employee.setRole(hrRole.get());
    Employee savedEmployee = employeeRepository.save(employee);
    return mapper.toHrResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployeeProfile(EmployeeRequest employeeRequest) {
        String userEmail = securityUtils.getCurrentUserDetails().getUsername();
        Optional<Employee> employee = employeeRepository.findByEmail(userEmail);
        if(employee.isPresent()){
            Employee existingEmployee = employee.get();
            BeanUtils.copyProperties(employeeRequest, existingEmployee);
            return mapper.mapToEmployeeResponse(employeeRepository.save(existingEmployee));
        }
        throw new RuntimeException("employee does not exist in the database");
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String userEmail = securityUtils.getCurrentUserDetails().getUsername();
        Employee existingEmployee = getEmployee(userEmail);
        String imageUrl = uploadDocument(multipartFile);
        existingEmployee.setImageUrl(imageUrl);
        existingEmployee.setUpdatedOn(LocalDateTime.now());
        employeeRepository.save(existingEmployee);
        return imageUrl;
    }

    @Override
    public String uploadResume(MultipartFile multipartFile) {
        String userEmail = securityUtils.getCurrentUserDetails().getUsername();
        Employee existingEmployee = getEmployee(userEmail);
        String resumeUrl = uploadDocument(multipartFile);
        existingEmployee.setResumeUrl(resumeUrl);
        existingEmployee.setUpdatedOn(LocalDateTime.now());
        employeeRepository.save(existingEmployee);
        return resumeUrl;
    }

    public String uploadDocument(MultipartFile multipartFile) {
        long fileSize = multipartFile.getSize();
        if (fileSize > 1000 * 1024){
            throw new RuntimeException("file too large, file should not exceed 1MB");
        }
        try {
            return cloudinary.uploader().upload(multipartFile.getBytes(),
                    Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployee(String userEmail) {
        Employee employee = employeeRepository.findByEmail(userEmail).orElseThrow(() -> new EmailNotFoundException("user with email " + userEmail + " does not exist"));
        return employee;
    }
}
