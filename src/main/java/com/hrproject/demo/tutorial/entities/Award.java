package com.hrproject.demo.tutorial.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Award extends BaseEntity{
    private String title;
    private String description;
    private int year;
    private LocalDateTime date;
}
