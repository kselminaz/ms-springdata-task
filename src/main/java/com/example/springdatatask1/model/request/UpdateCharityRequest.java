package com.example.springdatatask1.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCharityRequest {

    private Long ordering;
    private LocalDate startDate;
    private LocalDate endDate;
    private int requiredSteps;
    private int presentSteps;

}
