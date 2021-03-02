package com.lootfood.db.repository;

import com.lootfood.db.entity.City;
import com.lootfood.db.repository.custom.CityRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends MongoRepository<City, String>, CityRepositoryCustom {
    Page<City> findAll(Pageable pageable);
}