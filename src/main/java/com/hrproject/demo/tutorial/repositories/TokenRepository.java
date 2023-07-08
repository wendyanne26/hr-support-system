package com.hrproject.demo.tutorial.repositories;

import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByJwtToken(String jwtToken);
    List<Token> findTokenByRevokedFalseAndExpiredFalse(Employee employee);
}
