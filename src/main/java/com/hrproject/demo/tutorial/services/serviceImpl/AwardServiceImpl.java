package com.hrproject.demo.tutorial.services.serviceImpl;

import com.hrproject.demo.tutorial.dtos.requests.AwardRequest;
import com.hrproject.demo.tutorial.dtos.response.AwardResponse;
import com.hrproject.demo.tutorial.dtos.response.AwardResponseList;
import com.hrproject.demo.tutorial.entities.Award;
import com.hrproject.demo.tutorial.entities.Employee;
import com.hrproject.demo.tutorial.entities.Role;
import com.hrproject.demo.tutorial.exceptions.AwardNotFoundException;
import com.hrproject.demo.tutorial.exceptions.EmailNotFoundException;
import com.hrproject.demo.tutorial.mapper.Mapper;
import com.hrproject.demo.tutorial.repositories.AwardRepository;
import com.hrproject.demo.tutorial.repositories.EmployeeRepository;
import com.hrproject.demo.tutorial.securityconfig.utils.SecurityUtils;
import com.hrproject.demo.tutorial.services.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AwardServiceImpl implements AwardService {
    private final SecurityUtils securityUtils;
    private final EmployeeRepository employeeRepository;
    private final AwardRepository awardRepository;
    private final Mapper mapper;
    @Override
    public AwardResponse createAward(AwardRequest awardRequest) {
        UserDetails userDetails = securityUtils.getCurrentUserDetails();
        Employee employee = employeeRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()-> new EmailNotFoundException("user with email does not exist"));
        Role role = employee.getRole();
        if(!role.getName().equals("HR")){
            throw new RuntimeException("does not have required authorities to perform this action");
        }
        Award award = new Award();
        BeanUtils.copyProperties(awardRequest, award);
        award.setTitle(awardRequest.getTitle());
        award.setDescription(awardRequest.getDescription());
        award.setYear(LocalDate.now().getYear());
        Award savedAward = awardRepository.save(award);
        return mapper.toAwardResponse(savedAward);
    }

    @Override
    public Page<AwardResponse> getAwardByYear(String year, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Award> awardPage = awardRepository.findAwardByYear(Integer.parseInt(year), pageable);
    if(awardPage.isEmpty()){
        throw new AwardNotFoundException(String.format("no awards found for %s", year));
    }
    return awardPage.map(mapper::toAwardResponse);
    }

    @Override
    public AwardResponseList getAllRewards(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Award> awards = awardRepository.findAll(pageable);
        List<Award> pageReward = awards.getContent();
        if(awards.isEmpty()){
            throw new AwardNotFoundException(String.format("no awards found"));
        }
        var rewards = AwardResponseList.builder()
                .awardList(pageReward)
                .currentPage(awards.getNumber())
                .totalElement(awards.getTotalElements())
                .totalPage(awards.getTotalPages())
                .build();
        return rewards;
    }
}