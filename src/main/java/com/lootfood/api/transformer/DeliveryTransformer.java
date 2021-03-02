package com.lootfood.api.transformer;

import com.lootfood.api.dto.DeliveryDto;
import com.lootfood.db.entity.Delivery;

public class DeliveryTransformer {
    public static Delivery transform(DeliveryDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Delivery transform(String id, DeliveryDto dto) {
        return Delivery.builder()
                .id(id)
                .name(dto.getName())
                .couriers(dto.getCouriers())
                .build();
    }

    public static DeliveryDto transform(Delivery delivery) {
        return transform(delivery.getId(), delivery);
    }

    public static DeliveryDto transform(String id, Delivery delivery) {
        return DeliveryDto.builder()
                .id(id)
                .name(delivery.getName())
                .couriers(delivery.getCouriers())
                .build();
    }
}
