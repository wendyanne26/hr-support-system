package com.hrproject.demo.tutorial.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NextOfKin {
    private String name;
    private String relationship;
    private String occupation;
    private String phoneNumber;
    private String address;
}
