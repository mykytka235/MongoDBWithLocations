package com.lootfood.api.transformer;

import com.lootfood.api.dto.OrderDto;
import com.lootfood.entity.Order;

public class OrderTransformer {
    public static Order transform(OrderDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Order transform(String id, OrderDto dto) {
        return Order.builder().id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .user(dto.getUser())
                .service(dto.getService())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate())
                .build();
    }

    public static OrderDto transform(Order order) {
        return transform(order.getId(), order);
    }

    public static OrderDto transform(String id, Order order) {
        return OrderDto.builder().id(id)
                .name(order.getName())
                .description(order.getDescription())
                .user(order.getUser())
                .service(order.getService())
                .createdDate(order.getCreatedDate())
                .updateDate(order.getUpdateDate())
                .build();
    }
}
