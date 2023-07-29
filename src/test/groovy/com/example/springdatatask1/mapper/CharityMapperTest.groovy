package com.example.springdatatask1.mapper

import com.example.springdatatask1.dao.entity.CharityEntity
import com.example.springdatatask1.model.enums.CharityStatus
import com.example.springdatatask1.model.request.CharityRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.springdatatask1.mapper.CharityMapper.buildCharityEntity
import static com.example.springdatatask1.mapper.CharityMapper.buildCharityResponse

class CharityMapperTest extends Specification{

    private EnhancedRandom enhancedRandom= EnhancedRandomBuilder.aNewEnhancedRandom();

    def "Test buildCharityEntity"(){
        given:
        def request=enhancedRandom.nextObject(CharityRequest)
        when:
        def actual=buildCharityEntity(request)
        then:
        actual.status==CharityStatus.IN_PROGRESS
        actual.logo==request.logo
        actual.ordering==request.ordering
        actual.startDate==request.startDate
        actual.endDate==request.endDate
        actual.requiredSteps==request.requiredSteps
        actual.presentSteps==request.presentSteps

    }
    def "Test buildCharityResponse"(){
        given:
        def charity =enhancedRandom.nextObject(CharityEntity)
        when:
        def actual=buildCharityResponse(charity)
        then:
        actual.id==charity.id
        actual.logo==charity.logo
        actual.startDate==charity.startDate
        actual.endDate==charity.endDate
        actual.requiredSteps==charity.requiredSteps
        actual.presentSteps==charity.presentSteps
        actual.ordering==charity.ordering
        actual.status==charity.status

    }
}
