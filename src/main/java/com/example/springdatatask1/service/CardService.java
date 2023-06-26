package com.example.springdatatask1.service;

import com.example.springdatatask1.dao.repository.CardRepository;
import com.example.springdatatask1.model.response.CardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.springdatatask1.mapper.CardMapper.buildCardResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponse getCardById(Long id){

        var card=cardRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Card not found")
        );
        return buildCardResponse(card);



    }
}
