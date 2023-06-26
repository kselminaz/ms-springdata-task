package com.example.springdatatask1.service;


import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.dao.repository.CharityRepository;
import com.example.springdatatask1.mapper.CharityMapper;
import com.example.springdatatask1.model.enums.CharityStatus;
import com.example.springdatatask1.model.request.CharityRequest;
import com.example.springdatatask1.model.request.UpdateCharityRequest;
import com.example.springdatatask1.model.response.CharityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


}
