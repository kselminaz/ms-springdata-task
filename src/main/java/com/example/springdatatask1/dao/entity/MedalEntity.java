package com.example.springdatatask1.dao.entity;


import com.example.springdatatask1.model.enums.MedalStatus;
import com.example.springdatatask1.model.enums.MedalType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="medals")
@Builder
public class MedalEntity {

    @Id
    @GeneratedValue(strategy =IDENTITY)
    private Long id;

    private String image;

    private Long steps;

    private boolean periodic;

    @Enumerated(STRING)
    private MedalType medalType;

    private Long charityId;

    private int progressRate;

    @Enumerated(STRING)
    private MedalStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedalEntity that = (MedalEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
