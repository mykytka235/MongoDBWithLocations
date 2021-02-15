package com.skankhunt220.repository.city;

import com.skankhunt220.entity.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<City, String>, CityRepositoryCustom{
    City findFirstByName(String name);
}