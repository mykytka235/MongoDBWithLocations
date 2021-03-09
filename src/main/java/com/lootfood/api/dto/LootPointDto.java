package com.lootfood.api.dto;

import com.lootfood.db.entity.Address;
import com.lootfood.db.entity.Order;
import com.lootfood.db.entity.Status;
import com.lootfood.db.entity.Visibility;
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
    private Visibility visibility;
    private GeoJsonPoint location;
    private Address address;
    private Number total;
    private List<Order> orders;
    private String serviceId;
    private String deliveryId;
    private Status status;
    private Date createdDate;
    private Date expirationDate;
}
