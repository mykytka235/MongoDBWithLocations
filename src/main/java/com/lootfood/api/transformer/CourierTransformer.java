package com.lootfood.api.transformer;

import com.lootfood.api.dto.CourierDto;
import com.lootfood.db.entity.Courier;

public class CourierTransformer {
    public static Courier transform(CourierDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Courier transform(String id, CourierDto dto) {
        return Courier.builder()
                .id(id)
                .phone(dto.getPhone())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .location(dto.getLocation())
                .build();
    }

    public static CourierDto transform(Courier courier) {
        return transform(courier.getId(), courier);
    }

    public static CourierDto transform(String id, Courier courier) {
        return CourierDto.builder()
                .id(id)
                .phone(courier.getPhone())
                .firstName(courier.getFirstName())
                .lastName(courier.getLastName())
                .location(courier.getLocation())
                .build();
    }
}
