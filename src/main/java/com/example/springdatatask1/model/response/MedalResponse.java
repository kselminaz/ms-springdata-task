package com.example.springdatatask1.model.response;

import com.example.springdatatask1.model.enums.MedalStatus;
import com.example.springdatatask1.model.enums.MedalType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedalResponse {

    private Long id;
    private String image;
    private Long steps;
    private boolean periodic;
    private MedalType medalType;
    private Long charityId;
    private int progressRate;

}
