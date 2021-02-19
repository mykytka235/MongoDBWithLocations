package com.lootfood.api.transformer;

import com.lootfood.api.Dto.OrderDto;
import com.lootfood.entity.Order;

public class OrderTransformer {
    public static Order transform(OrderDto dto) {
        return Order.builder().id(dto.getId()).name(dto.getName()).user(dto.getUser()).service(dto.getService())
                .description(dto.getDescription()).createdDate(dto.getCreatedDate()).updateDate(dto.getUpdateDate())
                .build();
    }

    public static Order transform(String id, OrderDto dto) {
        return Order.builder().id(id).name(dto.getName()).user(dto.getUser()).service(dto.getService())
                .description(dto.getDescription()).createdDate(dto.getCreatedDate()).updateDate(dto.getUpdateDate())
                .build();
    }

    public static OrderDto transform(Order order) {
        return OrderDto.builder().id(order.getId()).name(order.getName()).user(order.getUser())
                .service(order.getService()).description(order.getDescription()).createdDate(order.getCreatedDate())
                .updateDate(order.getUpdateDate()).build();
    }

    public static OrderDto transform(String id, Order order) {
        return OrderDto.builder().id(id).name(order.getName()).user(order.getUser()).service(order.getService())
                .description(order.getDescription()).createdDate(order.getCreatedDate())
                .updateDate(order.getUpdateDate()).build();
    }
}
