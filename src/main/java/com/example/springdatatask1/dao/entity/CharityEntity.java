package com.example.springdatatask1.dao.entity;

import com.example.springdatatask1.model.enums.CharityStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "charities")
    @Builder
    public class CharityEntity {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        private String logo;

        private LocalDate startDate;

        private LocalDate endDate;

        private Long ordering;

        private int requiredSteps;

        private  int presentSteps;

        @Enumerated(STRING)
        private CharityStatus status;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;

        private LocalDateTime deletedAt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CharityEntity that = (CharityEntity) o;
           // return id.equals(charity.id);
            return Objects.equals(id,that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }


