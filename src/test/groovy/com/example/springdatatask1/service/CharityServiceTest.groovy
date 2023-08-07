package com.example.springdatatask1.service;

import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.dao.repository.CharityRepository
import com.example.springdatatask1.mapper.CharityMapper
import com.example.springdatatask1.mapper.CharityMapperTest
import com.example.springdatatask1.model.criteria.CharityCriteria
import com.example.springdatatask1.model.criteria.PageCriteria
import com.example.springdatatask1.model.enums.CharityStatus
import com.example.springdatatask1.model.request.CharityRequest
import com.example.springdatatask1.model.request.UpdateCharityRequest
import com.example.springdatatask1.model.response.PageableCharityResponse
import com.example.springdatatask1.service.specification.CharitySpecification;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort;
import spock.lang.Specification

import java.time.LocalDate;
import java.util.Optional

import static com.example.springdatatask1.mapper.CharityMapper.buildCharityEntity;

public class CharityServiceTest extends Specification {

    private EnhancedRandom random= EnhancedRandomBuilder.aNewEnhancedRandom();
    private CharityRepository charityRepository;
    private CharityService charityService;
    private AsyncCharityDonateService asyncCharityDonateService;

    void setup() {
        charityRepository=Mock();
        charityService=new CharityService(charityRepository,asyncCharityDonateService);

    }

    def "TestgetCharityById success"() {

        given:
        def id=random.nextLong()
        def charityEntity=random.nextObject(CharityEntity)
        when:
        def actual=charityService.getCharityById(id)
        then:
        1 * charityRepository.findById(id) >> Optional.of(charityEntity)
        actual.id==charityEntity.id
        actual.logo==charityEntity.logo
        actual.startDate==charityEntity.startDate
        actual.endDate==charityEntity.endDate
        actual.ordering==charityEntity.ordering
        actual.status==charityEntity.status
        actual.requiredSteps==charityEntity.requiredSteps
        actual.presentSteps==charityEntity.presentSteps

    }
    def "TestgetCharityById not found charity"() {

        given:
        def id=random.nextLong()
        when:
        charityService.getCharityById(id)
        then:
        1 * charityRepository.findById(id) >> Optional.empty()

        RuntimeException ex=thrown();
        ex.getMessage()=="Charity not found"

    }

    def "Test updateCharity success"(){
        given:
        def id=random.nextLong()
        def request=random.nextObject(UpdateCharityRequest)
        def charityEntity=random.nextObject(CharityEntity)
        when:
        charityService.updateCharity(id,request)
        then:
        1 * charityRepository.findById(id) >> Optional.of(charityEntity)
        1 * charityRepository.save(charityEntity)
        request.ordering==charityEntity.ordering
        request.startDate==charityEntity.startDate
        request.endDate==charityEntity.endDate
        request.requiredSteps==charityEntity.requiredSteps
        request.presentSteps==charityEntity.presentSteps


    }

    def "Test updateCharity error when charity not found"(){
        given:
        def id=random.nextLong()
        def request=random.nextObject(UpdateCharityRequest)
        when:
        charityService.updateCharity(id,request)
        then:
        1 * charityRepository.findById(id) >> Optional.empty()
        0 * charityRepository.save()

        RuntimeException ex=thrown()
        ex.message=="Charity not found"

    }

    def "Test getAll method"(){
        given:
        def charityEntity=random.nextObject(CharityEntity,"status")
        charityEntity.status=CharityStatus.IN_PROGRESS;

        when:
        def actual=charityService.getAll();
        then:
        1 * charityRepository.findAll() >> List.of(charityEntity)
        actual[0].id==charityEntity.id
        actual[0].logo==charityEntity.logo
        actual[0].startDate==charityEntity.startDate
        actual[0].endDate==charityEntity.endDate
        actual[0].ordering==charityEntity.ordering
        actual[0].status==charityEntity.status
        actual[0].requiredSteps==charityEntity.requiredSteps
        actual[0].presentSteps==charityEntity.presentSteps


    }
    def "TestDeleteCharity success"(){
        given:
        def id=random.nextLong();
        def charity=random.nextObject(CharityEntity)

        when:
        charityService.deleteCharity(id);

        then:
        1 * charityRepository.findById(id) >>Optional.of(charity)

        1 * charityRepository.save(charity);

        charity.status==CharityStatus.DELETED

    }
    def "TestDeleteCharity error"(){
        given:
        def id=random.nextLong()

        when:
        charityService.deleteCharity(id)

        then:
        1 * charityRepository.findById(id) >>Optional.empty()

        0 * charityRepository.save()

        RuntimeException ex=thrown()
        ex.message=="Charity not found"

    }
    def "TestUpdateCharityWithParam success"(){

        given:
        def id=random.nextLong()
        def status="COMPLETED"
        def charity=random.nextObject(CharityEntity,"status")
        charity.status=CharityStatus.valueOf(status)
        when:
        charityService.updateCharityWithParam(id,status)
        then:
        1 * charityRepository.findById(id) >> Optional.of(charity)
        1 * charityRepository.save(charity)

        charity.status==CharityStatus.valueOf(status)


    }
    def "TestUpdateCharityWithParam not found charity"(){

        given:
        def id=random.nextLong()
        def status="COMPLETED"

        when:
        charityService.updateCharityWithParam(id,status)
        then:
        1 * charityRepository.findById(id) >> Optional.empty()
        0 * charityRepository.save()

        RuntimeException ex=thrown()
        ex.message=="Charity not found"


    }
    def "TestSaveCharity"(){
        given:
        def request=random.nextObject(CharityRequest)
        def charityEntity=CharityEntity.builder()
                .status(CharityStatus.IN_PROGRESS)
                .logo(request.getLogo())
                .ordering(request.getOrdering())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .requiredSteps(request.getRequiredSteps())
                .presentSteps(request.getPresentSteps())
                .build()
        when:
        def actual=charityService.saveCharity(request)
        then:
        1 * charityRepository.save(charityEntity)

    }

    def "TestPageableCharityResponse"(){
        given:
         def pageCriteria=random.nextObject(PageCriteria)
         def charityCriteria=random.nextObject(CharityCriteria)

        when:
        def actual=charityService.pageableCharityResponse(pageCriteria,charityCriteria)
        then:
        1 * charityRepository.findAll(new CharitySpecification(charityCriteria),
        PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending()))
        >> PageableCharityResponse

    }


}
