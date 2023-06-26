package com.example.springdatatask1.controller;

import com.example.springdatatask1.model.request.CharityRequest;
import com.example.springdatatask1.model.request.UpdateCharityRequest;
import com.example.springdatatask1.model.response.CharityResponse;
import com.example.springdatatask1.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/charities")
@RequiredArgsConstructor
public class CharityController {

    private final CharityService charityService;

    @GetMapping("/{id}")
    public CharityResponse getCharityByID(@PathVariable Long id) {
        return charityService.getCharityById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCharity(@RequestBody CharityRequest charityRequest){
        charityService.saveCharity(charityRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCharity(@PathVariable Long id, @RequestBody UpdateCharityRequest request){
        charityService.updateCharity(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCharity(@PathVariable Long id){
        charityService.deleteCharity(id);
    }
    @GetMapping
    public List<CharityResponse> getAll(){
        return charityService.getAll();
    }

}
