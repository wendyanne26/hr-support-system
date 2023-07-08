package com.hrproject.demo.tutorial.dtos.response;

import com.hrproject.demo.tutorial.entities.Award;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class AwardResponseList {
    private List<Award> awardList;
    private Long totalElement;
    private int totalPage;
    private int currentPage;
}
