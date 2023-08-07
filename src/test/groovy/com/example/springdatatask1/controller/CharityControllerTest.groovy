package com.example.springdatatask1.controller

import com.example.springdatatask1.model.enums.CharityStatus
import com.example.springdatatask1.model.request.CharityRequest
import com.example.springdatatask1.model.request.UpdateCharityRequest
import com.example.springdatatask1.model.response.CharityResponse
import com.example.springdatatask1.service.CharityService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXException
import org.xml.sax.SAXParseException

import java.time.LocalDate

class CharityControllerTest extends Specification {

    private EnhancedRandom random= EnhancedRandomBuilder.aNewEnhancedRandom();
    private CharityService charityService;
    private MockMvc mockMvc;

    void setup(){

        charityService= Mock();
        def charityController=new CharityController(charityService);
        mockMvc= MockMvcBuilders.standaloneSetup(charityController)
  /*      .setControllerAdvice(new ErrorHandler())*/
        .build()
    }

    def "TestGetCharityById"(){
        given:
        def id=1L
        def url="/v1/charities/$id"

        def responseView=new CharityResponse(1,"test.jpg",LocalDate.of(2023,05,05),
        LocalDate.of(2023,06,06),2,CharityStatus.IN_PROGRESS,50000,0)
        def expectedResponse='''
                                    {
                                "id": 1,
                                "logo":"test.jpg",
                                "startDate":[2023,05,05],
                                "endDate":[2023,06,06],
                                "ordering":2,
                                "status":"IN_PROGRESS",
                                "requiredSteps":50000,
                                "presentSteps":0
                                    }
                                 '''
        when:
        def result=mockMvc.perform(
                get(url)
        ).andReturn()
        then:
        1 * charityService.getCharityById(id) >> responseView
        def response=result.response
        response.status==HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse,response.getContentAsString(),false)

    }
    def "TestSaveCharity"(){

        given:
        def url="/v1/charities"
        def dto=new CharityRequest("test.jpg",1, LocalDate.of(2023,05,05),
        LocalDate.of(2023,06,06),50000,0)
        def requestBody='''
                            {
                                "logo":"test.jpg",
                                "ordering":1,
                                "startDate":"2023-05-05",
                                "endDate":"2023-06-06",
                                "requiredSteps":50000,
                                "presentSteps":0
                            }
       '''
        when:
        def result=mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody)
        ).andReturn()
        then:
        1 * charityService.saveCharity(dto)
        def response=result.response
        response.status== HttpStatus.CREATED.value()
    }

    def "TestUpdateCharity"(){

        given:
        def id=1L
        def url="/v1/charities/$id"
        def dto=new UpdateCharityRequest(1, LocalDate.of(2023,05,05),
                LocalDate.of(2023,06,06),50000,0)
        def requestBody='''
                            {
                                "ordering":1,
                                "startDate":"2023-05-05",
                                "endDate":"2023-06-06",
                                "requiredSteps":50000,
                                "presentSteps":0
                            }
       '''
        when:
        def result=mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn()

        then:
        1 * charityService.updateCharity(id,dto)
        def response=result.response
        response.status== HttpStatus.NO_CONTENT.value()
    }

    def "TestUpdateCharityWithParam"(){

        given:
        def id=1L
        def url="/v1/charities/$id"
        def status="COMPLETED"

        when:
        def result=mockMvc.perform(patch(url)
                .param("status","COMPLETED")
        ).andReturn()

        then:
        1 * charityService.updateCharityWithParam(id,status)
        def response=result.response
        response.status== HttpStatus.NO_CONTENT.value()
    }

    def "TestDeleteCharity"(){

        given:
        def id=1L
        def url="/v1/charities/$id"

        when:
        def result=mockMvc.perform(delete(url)
        ).andReturn()

        then:
        1 * charityService.deleteCharity(id)
        def response=result.response
        response.status== HttpStatus.NO_CONTENT.value()
    }
}
