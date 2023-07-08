package com.hrproject.demo.tutorial.services.serviceImpl;

import com.hrproject.demo.tutorial.dtos.requests.AuthenticationRequest;
import com.hrproject.demo.tutorial.dtos.response.AuthenticationResponse;
import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Token;
import com.hrproject.demo.tutorial.exceptions.EmailNotFoundException;
import com.hrproject.demo.tutorial.repositories.EmployeeRepository;
import com.hrproject.demo.tutorial.repositories.TokenRepository;
import com.hrproject.demo.tutorial.securityconfig.utils.JwtUtils;
import com.hrproject.demo.tutorial.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        Employee employee = employeeRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()-> new EmailNotFoundException("user with email "+ authenticationRequest.getEmail()+ " not fpund"));
        employee.setLoggedIn(true);
        employeeRepository.save(employee);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        revokeToken(employee);
        var userToken = jwtUtils.generateToken(employee);
        saveToken(employee, userToken);

        return AuthenticationResponse.builder()
                .token(userToken)
                .issuedAt(jwtUtils.getIssuedAt(userToken))
                .expiredAt(jwtUtils.getExpiredAt(userToken))
                .build();
    }

    private void saveToken(Employee employee, String userToken) {
        Token token = Token.builder()
                .jwtToken(userToken)
                .revoked(false)
                .expired(false)
                .employee(employee)
                .build();
        tokenRepository.save(token);
    }
    private void revokeToken(Employee employee) {
        var token = tokenRepository.findTokenByRevokedFalseAndExpiredFalse(employee);
        if(token.isEmpty()){
            return;
        }
        token.forEach(token1 -> {
            token1.setRevoked(true);
            token1.setExpired(true);
        });
        tokenRepository.saveAll(token);
    }

}
