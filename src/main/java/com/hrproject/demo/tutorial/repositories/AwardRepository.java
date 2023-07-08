package com.hrproject.demo.tutorial.repositories;

import com.hrproject.demo.tutorial.entities.Award;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AwardRepository extends MongoRepository<Award, String> {
    Page<Award> findAwardByYear(int year, Pageable pageable);
}
