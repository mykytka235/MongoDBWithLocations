package com.lootfood.api.transformer;

import com.lootfood.api.dto.LootPointDto;
import com.lootfood.db.entity.LootPoint;

public class LootPointTransformer {
    public static LootPoint transform(LootPointDto dto) {
        return transform(dto.getId(), dto);
    }

    public static LootPoint transform(String id, LootPointDto dto) {
        return LootPoint.builder().id(id)
                .visibility(dto.getVisibility())
                .location(dto.getLocation())
                .address(dto.getAddress())
                .total(dto.getTotal())
                .orders(dto.getOrders())
                .serviceId(dto.getServiceId())
                .deliveryId(dto.getDeliveryId())
                .createdDate(dto.getCreatedDate())
                .expirationDate(dto.getExpirationDate())
                .status(dto.getStatus())
                .build();
    }

    public static LootPointDto transform(LootPoint lootPoint) {
        return transform(lootPoint.getId(), lootPoint);
    }

    public static LootPointDto transform(String id, LootPoint lootPoint) {
        return LootPointDto.builder().id(id)
                .visibility(lootPoint.getVisibility())
                .location(lootPoint.getLocation())
                .address(lootPoint.getAddress())
                .total(lootPoint.getTotal())
                .orders(lootPoint.getOrders())
                .serviceId(lootPoint.getServiceId())
                .deliveryId(lootPoint.getDeliveryId())
                .createdDate(lootPoint.getCreatedDate())
                .expirationDate(lootPoint.getExpirationDate())
                .status(lootPoint.getStatus())
                .build();
    }
}
