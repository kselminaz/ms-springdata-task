package com.example.springdatatask1.service;

import com.example.springdatatask1.dao.entity.MedalEntity;
import com.example.springdatatask1.dao.repository.MedalRepository;
import com.example.springdatatask1.mapper.MedalMapper;
import com.example.springdatatask1.model.enums.MedalStatus;
import com.example.springdatatask1.model.request.MedalRequest;
import com.example.springdatatask1.model.request.UpdateMedalRequest;
import com.example.springdatatask1.model.response.MedalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springdatatask1.mapper.MedalMapper.*;
import static com.example.springdatatask1.model.enums.MedalStatus.DELETED;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedalService {

    private final MedalRepository medalRepository;

    public MedalResponse getMedalById(Long id){

        var medal=medalFindOrFail(id);
        return buildMedalResponse(medal);
    }
    public List<MedalResponse> getAll(){

        return medalRepository.findAll()
                .stream()
                .map(MedalMapper::buildMedalResponse)
                .collect(Collectors.toList());
    }

    public void saveMedal(MedalRequest medalRequest){
        var medalEntity=buildMedalEntity(medalRequest);
        medalRepository.save(medalEntity);
    }
    public void deleteMedal(Long id){
        var medal=medalFindOrFail(id);
        medal.setStatus(DELETED);
        medal.setDeletedAt(LocalDateTime.now());
        medalRepository.save(medal);
    }
    public void medalUpdate(Long id,UpdateMedalRequest request){

        var medal=medalFindOrFail(id);
        updateMedalEntity(medal,request);
    }
    private MedalEntity medalFindOrFail(Long id){
        return  medalRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Medal not found")
        );
    }

}
