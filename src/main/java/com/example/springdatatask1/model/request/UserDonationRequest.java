package com.example.springdatatask1.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDonationRequest {
    private Long userId;
    private Long charityId;
    private Long steps;
    private Double amount;
    private Boolean inRaiting;
}
