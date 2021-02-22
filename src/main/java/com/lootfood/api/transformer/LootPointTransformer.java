package com.lootfood.api.transformer;

import com.lootfood.api.dto.LootPointDto;
import com.lootfood.entity.LootPoint;

public class LootPointTransformer {
    public static LootPoint transform(LootPointDto dto) {
        return transform(dto.getId(), dto);
    }

    public static LootPoint transform(String id, LootPointDto dto) {
        return LootPoint.builder().id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .type(dto.getType())
                .orders(dto.getOrders())
                .location(dto.getLocation())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate())
                .build();
    }

    public static LootPointDto transform(LootPoint lootPoint) {
        return transform(lootPoint.getId(), lootPoint);
    }

    public static LootPointDto transform(String id, LootPoint lootPoint) {
        return LootPointDto.builder().id(id)
                .name(lootPoint.getName())
                .description(lootPoint.getDescription())
                .type(lootPoint.getType())
                .orders(lootPoint.getOrders())
                .location(lootPoint.getLocation())
                .createdDate(lootPoint.getCreatedDate())
                .updateDate(lootPoint.getUpdateDate()).build();
    }
}
