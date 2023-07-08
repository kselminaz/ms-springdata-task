package com.example.springdatatask1.client;

import com.example.springdatatask1.model.request.UserDonationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Profile(value = "default")
@FeignClient(name = "ms-users",
             url="http://localhost:8081")
public interface UserClient {

    @PostMapping("/v1/user-donations/")
    void saveUserDonation(@RequestBody UserDonationRequest request);

}
