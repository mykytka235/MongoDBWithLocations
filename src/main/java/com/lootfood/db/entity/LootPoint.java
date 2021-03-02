package com.lootfood.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Visibility visibility;
    private GeoJsonPoint location;
    private Address address;
    private Number total;
    private List<Order> orders;
    private String serviceId;
    private String deliveryId;
    private Status status;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updateDate;
    private Date expirationDate;
}