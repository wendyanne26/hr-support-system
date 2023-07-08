package com.hrproject.demo.tutorial.services;

import com.hrproject.demo.tutorial.dtos.requests.AwardRequest;
import com.hrproject.demo.tutorial.dtos.response.AwardResponse;
import com.hrproject.demo.tutorial.dtos.response.AwardResponseList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface AwardService {
    AwardResponse createAward(AwardRequest awardRequest);

    Page<AwardResponse> getAwardByYear(String year, int page, int size);

    AwardResponseList getAllRewards(int page, int size, Sort sort);
}
