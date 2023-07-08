package com.example.springdatatask1.model.response;

import com.example.springdatatask1.model.enums.CharityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableCharityResponse {
    private Long id;
    private String logo;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long ordering;
    private CharityStatus status;
    private int requiredSteps;
    private  int presentSteps;
}
