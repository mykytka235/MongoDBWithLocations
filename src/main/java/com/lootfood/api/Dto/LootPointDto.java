package com.lootfood.api.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lootfood.entity.Order;
import com.lootfood.entity.TypeOfLootPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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
    private TypeOfLootPoint type;
    private List<Order> orders;
    private GeoJsonPoint location;
    private Date createdDate;
    private Date updateDate;
}
