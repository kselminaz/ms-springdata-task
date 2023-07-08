package com.example.springdatatask1.model.criteria;

import com.example.springdatatask1.model.enums.CharityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharityCriteria {
    private CharityStatus status;
    private LocalDate dateFrom;
   /* private LocalDate dateTo;*/
    private Integer requiredSteps;
}
