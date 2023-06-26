package com.example.springdatatask1.dao.entity;


import com.example.springdatatask1.model.enums.CardStatus;
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
@Table(name = "cards")

public class CardEntity {
      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private String pan;

      private String cvv;

      @Column(name = "expire_date")
      private LocalDate expireDate;

      private String cardholder;

      @Enumerated(STRING)
      private CardStatus status;

      @CreationTimestamp
      private LocalDateTime createdAt;

      @UpdateTimestamp
      private LocalDateTime updatedAt;

      //check jpa buddy
      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CardEntity that = (CardEntity) o;
            return id.equals(that.id);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id);
      }
}
