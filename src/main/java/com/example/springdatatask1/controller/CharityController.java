package com.example.springdatatask1.controller;

import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.model.criteria.CharityCriteria;
import com.example.springdatatask1.model.criteria.PageCriteria;
import com.example.springdatatask1.model.request.CharityDonationRequest;
import com.example.springdatatask1.model.request.CharityRequest;
import com.example.springdatatask1.model.request.UpdateCharityRequest;
import com.example.springdatatask1.model.response.CharityResponse;
import com.example.springdatatask1.model.response.PageableCharityResponse;
import com.example.springdatatask1.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PatchMapping ("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCharityWithParam(@PathVariable Long id,@RequestParam String status){
        charityService.updateCharityWithParam(id,status);
    }
   /* @GetMapping
   public List<CharityResponse> getAll(){
        return charityService.getAll();
    }*/

    @GetMapping
    public PageableCharityResponse getCharities(PageCriteria pageCriteria, CharityCriteria charityCriteria){
      return charityService.pageableCharityResponse(pageCriteria,charityCriteria);
    }
    @PostMapping("/{id}/user-donate")
    public void userDonate(@PathVariable Long id,@RequestBody CharityDonationRequest request){

         charityService.charityDonate(id,request);

    }


}
