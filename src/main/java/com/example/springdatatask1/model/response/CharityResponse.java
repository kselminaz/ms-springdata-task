package com.example.springdatatask1.model.response;

import com.example.springdatatask1.model.enums.CharityStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharityResponse {

    private Long id;
    private String logo;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long ordering;
    private CharityStatus status;
    private int requiredSteps;
    private  int presentSteps;

}
