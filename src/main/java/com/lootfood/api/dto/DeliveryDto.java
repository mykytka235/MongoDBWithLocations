package com.lootfood.api.dto;

import com.lootfood.db.entity.Courier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private String id;
    private String name;
    private List<Courier> couriers;
}