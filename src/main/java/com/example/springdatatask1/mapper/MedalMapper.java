package com.example.springdatatask1.mapper;


import com.example.springdatatask1.dao.entity.MedalEntity;
import com.example.springdatatask1.model.enums.MedalStatus;
import com.example.springdatatask1.model.enums.MedalType;
import com.example.springdatatask1.model.request.MedalRequest;
import com.example.springdatatask1.model.request.UpdateMedalRequest;
import com.example.springdatatask1.model.response.MedalResponse;

import static com.example.springdatatask1.model.enums.MedalStatus.ACTIVE;

public class MedalMapper {

    public static MedalResponse buildMedalResponse(MedalEntity medal){

        return MedalResponse.builder()

                .id(medal.getId())
                .image(medal.getImage())
                .steps(medal.getSteps())
                .periodic(medal.isPeriodic())
                .medalType(medal.getMedalType())
                .charityId(medal.getCharityId())
                .progressRate(medal.getProgressRate())
                .build();
    }
    public static MedalEntity buildMedalEntity(MedalRequest request){

        return MedalEntity.builder()
                .image(request.getImage())
                .steps(request.getSteps())
                .periodic(request.getIsPeriodic())
                .medalType(request.getMedalType())
                .status(ACTIVE)
                .charityId(request.getCharityId())
                .progressRate(request.getProgressRate())
                .build();

    }
    public static void updateMedalEntity(MedalEntity medal, UpdateMedalRequest request){

                 medal.setSteps(request.getSteps());
                 medal.setMedalType(request.getMedalType());
                 medal.setPeriodic(request.isPeriodic());

    }

}
