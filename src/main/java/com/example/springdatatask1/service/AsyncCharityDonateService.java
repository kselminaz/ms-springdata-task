package com.example.springdatatask1.service;

import com.example.springdatatask1.client.UserClient;
import com.example.springdatatask1.model.constants.CharityStepConstants;
import com.example.springdatatask1.model.request.CharityDonationRequest;
import com.example.springdatatask1.model.request.UserDonationRequest;
import lombok.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.example.springdatatask1.model.constants.CharityStepConstants.STEP_TO_AMOUNT;
@Service
@Data
@RequiredArgsConstructor
public class AsyncCharityDonateService {

    private final UserClient userClient;


    @Async
    public void saveUserDonation(Long id, CharityDonationRequest request){
        userClient.saveUserDonation(new UserDonationRequest(request.getUserId(),id, request.getSteps(),STEP_TO_AMOUNT*request.getSteps(), request.getInRaiting()));
    }

}
