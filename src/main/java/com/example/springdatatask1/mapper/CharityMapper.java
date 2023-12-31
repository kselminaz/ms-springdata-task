package com.example.springdatatask1.mapper;

import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.model.enums.CharityStatus;
import com.example.springdatatask1.model.request.CharityRequest;
import com.example.springdatatask1.model.request.UpdateCharityRequest;
import com.example.springdatatask1.model.response.CharityResponse;
import com.example.springdatatask1.model.response.PageableCharityResponse;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;


public class CharityMapper{
    public static CharityResponse buildCharityResponse(CharityEntity charity){

        return CharityResponse.builder()

                .id(charity.getId())
                .logo(charity.getLogo())
                .startDate(charity.getStartDate())
                .endDate(charity.getEndDate())
                .requiredSteps(charity.getRequiredSteps())
                .presentSteps(charity.getPresentSteps())
                .ordering(charity.getOrdering())
                .status(charity.getStatus())
                .build();

    }

    public static CharityEntity buildCharityEntity(CharityRequest charity){

        return CharityEntity.builder()
                      .status(CharityStatus.IN_PROGRESS)
                      .logo(charity.getLogo())
                      .ordering(charity.getOrdering())
                      .startDate(charity.getStartDate())
                      .endDate(charity.getEndDate())
                      .requiredSteps(charity.getRequiredSteps())
                      .presentSteps(charity.getPresentSteps())
                      .build();

    }
    public static void updateCharityEntity(CharityEntity charity, UpdateCharityRequest request){

                        charity.setOrdering(request.getOrdering());
                        charity.setStartDate(request.getStartDate());
                        charity.setEndDate(request.getEndDate());
                        charity.setRequiredSteps(request.getRequiredSteps());
                        charity.setPresentSteps(request.getPresentSteps());

    }
    public static PageableCharityResponse buildPageableCharityResponse(Page<CharityEntity> charityPage){

            return PageableCharityResponse.builder()

                    .charities(charityPage.getContent().stream().map(CharityMapper::buildCharityResponse).collect(Collectors.toList()))
                    .hasNextPage(charityPage.hasNext())
                    .lastPageNumber(charityPage.getTotalPages())
                    .totalElements(charityPage.getTotalElements())
                    .build();

    }




}
