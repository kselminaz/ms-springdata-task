package com.example.springdatatask1.service;


import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.dao.repository.CharityRepository;
import com.example.springdatatask1.mapper.CharityMapper;
import com.example.springdatatask1.model.criteria.CharityCriteria;
import com.example.springdatatask1.model.criteria.PageCriteria;
import com.example.springdatatask1.model.enums.CharityStatus;
import com.example.springdatatask1.model.request.CharityDonationRequest;
import com.example.springdatatask1.model.request.CharityRequest;
import com.example.springdatatask1.model.request.UpdateCharityRequest;
import com.example.springdatatask1.model.response.CharityResponse;
import com.example.springdatatask1.model.response.PageableCharityResponse;
import com.example.springdatatask1.service.specification.CharitySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springdatatask1.mapper.CharityMapper.*;
import static com.example.springdatatask1.model.enums.CharityStatus.DELETED;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharityService {

    private final CharityRepository charityRepository;
    private final AsyncCharityDonateService asyncCharityDonateService;
    public CharityResponse getCharityById(Long id){
        var charity=fetchCharityIfExist(id);
        return buildCharityResponse(charity);
    }
    public List<CharityResponse> getAll(){
        return  charityRepository.findAll()
                .stream()
                .map(CharityMapper::buildCharityResponse)
                .collect(Collectors.toList());
    }
    public void saveCharity(CharityRequest charityRequest){

        charityRepository.save(buildCharityEntity(charityRequest));
    }
    public void updateCharity(Long id, UpdateCharityRequest updateCharityRequest){

        var charity=fetchCharityIfExist(id);

        updateCharityEntity(charity,updateCharityRequest);

        charityRepository.save(charity);
    }

    public void  deleteCharity(Long id){

        var charity=fetchCharityIfExist(id);
        charity.setStatus(DELETED);
        charity.setDeletedAt(LocalDateTime.now());
        charityRepository.save(charity);
    }

    private CharityEntity fetchCharityIfExist(Long id){
        return charityRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Charity not found")
        );
    }

    public Page<CharityEntity> pageableCharityResponse(PageCriteria pageCriteria, CharityCriteria charityCriteria){

        var charityEntityPage=charityRepository.findAll(
                new CharitySpecification(charityCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending())
                );

        return charityEntityPage;

        /*List<CharityEntity> charities=charityEntityPage.getContent();
        return charities.stream().map(CharityMapper::buildCharityResponse).collect(Collectors.toList());
*/
    }

    public void charityDonate(Long id,CharityDonationRequest request){

        var charity=fetchCharityIfExist(id);
        if(charity.getStatus()!= CharityStatus.IN_PROGRESS)
            throw new RuntimeException("Charity is not active");
        asyncCharityDonateService.saveUserDonation(id,request);

    }
}
