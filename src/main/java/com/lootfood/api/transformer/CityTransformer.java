package com.lootfood.api.transformer;

import com.lootfood.api.dto.CityDto;
import com.lootfood.entity.City;

public class CityTransformer {
    public static City transform(CityDto dto) {
        return transform(dto.getId(), dto);
    }

    public static City transform(String id, CityDto dto) {
        return City.builder()
                .id(id)
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public static CityDto transform(City city) {
        return transform(city.getId(), city);
    }

    public static CityDto transform(String id, City city) {
        return CityDto.builder()
                .id(id)
                .name(city.getName())
                .location(city.getLocation())
                .build();
    }
}
