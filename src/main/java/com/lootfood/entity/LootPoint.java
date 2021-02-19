package com.lootfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lootpoint")
public class LootPoint {
    @Id
    private String id;
    private String name;
    private String description;
    private TypeOfLootPoint type;
    private List<Order> orders;
    private GeoJsonPoint location;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updateDate;
}
