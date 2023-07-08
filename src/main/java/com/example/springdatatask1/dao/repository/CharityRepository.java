package com.example.springdatatask1.dao.repository;

import com.example.springdatatask1.dao.entity.CharityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CharityRepository extends JpaRepository<CharityEntity, Long>, JpaSpecificationExecutor<CharityEntity> {

}
