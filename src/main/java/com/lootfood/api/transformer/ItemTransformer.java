package com.lootfood.api.transformer;

import com.lootfood.api.dto.ItemDto;
import com.lootfood.db.entity.Item;

public class ItemTransformer {
    public static Item transform(ItemDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Item transform(String id, ItemDto dto) {
        return Item.builder()
                .id(id)
                .name(dto.getName())
                .serviceId(dto.getServiceId())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .price(dto.getPrice())
                .build();
    }

    public static ItemDto transform(Item item) {
        return transform(item.getId(), item);
    }

    public static ItemDto transform(String id, Item item) {
        return ItemDto.builder()
                .id(id)
                .name(item.getName())
                .serviceId(item.getServiceId())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .price(item.getPrice())
                .build();
    }
}
