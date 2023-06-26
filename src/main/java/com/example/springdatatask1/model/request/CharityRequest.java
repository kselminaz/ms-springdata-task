package com.example.springdatatask1.model.request;

import com.example.springdatatask1.model.enums.CharityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityRequest {

    private String logo;
    private Long ordering;
    private LocalDate startDate;
    private LocalDate endDate;
    private int requiredSteps;
    private int presentSteps;


}
