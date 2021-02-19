package com.lootfood.repository.city;

import com.lootfood.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<City, String>, CityRepositoryCustom {
    City findFirstByName(String name);
    Page<City> findAll(Pageable pageable);
}