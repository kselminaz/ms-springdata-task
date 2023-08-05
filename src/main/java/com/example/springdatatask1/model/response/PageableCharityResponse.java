package com.example.springdatatask1.model.response;

import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.model.enums.CharityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableCharityResponse {
    private List<CharityResponse> charities;
    private boolean hasNextPage;
    private int lastPageNumber;
    private Long totalElements;

}
