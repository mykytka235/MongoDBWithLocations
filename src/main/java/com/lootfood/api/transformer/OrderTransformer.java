package com.lootfood.api.transformer;

import com.lootfood.api.dto.OrderDto;
import com.lootfood.db.entity.Order;

public class OrderTransformer {
    public static Order transform(OrderDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Order transform(String id, OrderDto dto) {
        return Order.builder().id(id)
                .orderLines(dto.getOrderLines())
                .total(dto.getTotal())
                .build();
    }

    public static OrderDto transform(Order order) {
        return transform(order.getId(), order);
    }

    public static OrderDto transform(String id, Order order) {
        return OrderDto.builder().id(id)
                .orderLines(order.getOrderLines())
                .total(order.getTotal())
                .build();
    }
}
