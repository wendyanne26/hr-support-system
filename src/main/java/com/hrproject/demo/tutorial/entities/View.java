package com.hrproject.demo.tutorial.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class View extends BaseEntity{
    private String employeeId;
    private boolean hasView;
    @CreatedDate
    private LocalDateTime dateView;
}
