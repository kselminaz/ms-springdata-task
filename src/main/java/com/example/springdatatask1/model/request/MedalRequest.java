package com.example.springdatatask1.model.request;


import com.example.springdatatask1.model.enums.MedalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedalRequest {

    private String image;
    private Long steps;
    private Boolean isPeriodic;
    private MedalType medalType;
    private Long charityId;
    private int progressRate;


}
