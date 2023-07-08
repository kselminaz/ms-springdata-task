package com.example.springdatatask1.service.specification;


import com.example.springdatatask1.dao.entity.CharityEntity;
import com.example.springdatatask1.model.criteria.CharityCriteria;
import com.example.springdatatask1.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.time.LocalDate;

import static com.example.springdatatask1.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class CharitySpecification implements Specification<CharityEntity> {

    private CharityCriteria charityCriteria;


    @Override
    public Predicate toPredicate(Root<CharityEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

         Predicate pr1=cb.equal(root.get("status"),charityCriteria.getStatus());
         Predicate pr2=cb.greaterThanOrEqualTo(root.get("startDate"),charityCriteria.getDateFrom());
         Predicate pr3=cb.equal(root.get("requiredSteps"),charityCriteria.getRequiredSteps());
     /*   var predicates= PredicateUtil.builder()
                        .addNullSafety(charityCriteria.getStatus(),
                                status-> cb.like(root.get("status"),applyLikePattern(status))
                        )
                        .addNullSafety(charityCriteria.getDateFrom(),
                                        dateFrom-> cb.greaterThanOrEqualTo(root.get("startDate"),dateFrom)
                        )
                        .addNullSafety(charityCriteria.getRequiredSteps(),
                                requiredSteps->cb.equal(root.get("requiredSteps"),requiredSteps)
                        )
                        .build();*/
               return cb.and(pr1,pr3);

    }
}
