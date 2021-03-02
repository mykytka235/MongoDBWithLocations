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
@Document(collection = "service")
public class Service {
    @Id
    private String id;
    private String companyId;
    private String name;
    private GeoJsonPoint location;
    private List<Delivery> deliveries;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updateDate;
}
