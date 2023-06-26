package com.example.springdatatask1.dao.repository;

import com.example.springdatatask1.dao.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity,Long> {



}
