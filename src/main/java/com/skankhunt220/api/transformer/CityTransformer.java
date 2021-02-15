package com.skankhunt220.api.transformer;

import com.skankhunt220.entity.City;

public class CityTransformer {
    public static City transform(String id, City city){
        return City.builder().id(id)
                .name(city.getName())
                .location(city.getLocation())
                .build();
    }
}
