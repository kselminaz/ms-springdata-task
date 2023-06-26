package com.example.springdatatask1.mapper;

import com.example.springdatatask1.dao.entity.CardEntity;
import com.example.springdatatask1.model.response.CardResponse;

public class CardMapper {

    public static CardResponse buildCardResponse(CardEntity card){

        return CardResponse.builder()
                        .id(card.getId())
                        .cardholder(card.getCardholder())
                        .expireDate(card.getExpireDate())
                         .cvv(card.getCvv())
                         .pan(card.getPan()).build();
    }
}
