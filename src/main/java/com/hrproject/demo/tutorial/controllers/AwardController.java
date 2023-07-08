package com.hrproject.demo.tutorial.controllers;

import com.hrproject.demo.tutorial.dtos.requests.AwardRequest;
import com.hrproject.demo.tutorial.dtos.response.AwardResponse;
import com.hrproject.demo.tutorial.dtos.response.AwardResponseList;
import com.hrproject.demo.tutorial.services.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/award")
public class AwardController {
    private final AwardService awardService;
    @PostMapping("/createAward")
    public ResponseEntity<AwardResponse> createAward(@RequestBody AwardRequest awardRequest){
        return new ResponseEntity<>(awardService.createAward(awardRequest), HttpStatus.CREATED);
    }
    @PostMapping("/{year}")
    public ResponseEntity<Page<AwardResponse>> getAwardByYear(@PathVariable String year,
                                                              @RequestParam (value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size){
        Page<AwardResponse> response = awardService.getAwardByYear(year, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/allAward")
    public ResponseEntity<AwardResponseList> allRewards(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size, Sort sort){
        AwardResponseList response1 = awardService.getAllRewards(page, size, sort);
        return new ResponseEntity<>(response1, HttpStatus.OK);
    }
}
