package com.example.springdatatask1.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityDonationRequest {

    private Long userId;
    private Long steps;
    private Boolean inRaiting;
}
