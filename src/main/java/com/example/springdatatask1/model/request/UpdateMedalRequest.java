package com.example.springdatatask1.model.request;

import com.example.springdatatask1.model.enums.MedalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMedalRequest {

    private Long steps;
    private MedalType medalType;
    private boolean isPeriodic;
}
