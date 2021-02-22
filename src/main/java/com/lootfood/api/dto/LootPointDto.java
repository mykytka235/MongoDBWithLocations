package com.lootfood.api.dto;

import com.lootfood.entity.Order;
import com.lootfood.entity.LootPointType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LootPointDto {
    private String id;
    private String name;
    private String description;
    private LootPointType type;
    private List<Order> orders;
    private GeoJsonPoint location;
    private Date createdDate;
    private Date updateDate;
}
