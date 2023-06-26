package com.example.springdatatask1.dao.repository;

import com.example.springdatatask1.dao.entity.MedalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedalRepository extends JpaRepository<MedalEntity,Long> {
}
