package com.example.springdatatask1.controller;

import com.example.springdatatask1.model.request.MedalRequest;
import com.example.springdatatask1.model.request.UpdateMedalRequest;
import com.example.springdatatask1.model.response.MedalResponse;
import com.example.springdatatask1.service.MedalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/medals")
public class MedalController {

    private final MedalService medalService;

    @GetMapping("/{id}")
    public MedalResponse getMedalById(@PathVariable Long id){
        return medalService.getMedalById(id);
    }
    @GetMapping
    public List<MedalResponse> getAll(){
        return medalService.getAll();
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public void saveMedal(@RequestBody MedalRequest request){

        medalService.saveMedal(request);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteMedal(@PathVariable Long id){

        medalService.deleteMedal(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateMedal(@PathVariable Long id, @RequestBody UpdateMedalRequest request){
        medalService.medalUpdate(id,request);
    }
}
