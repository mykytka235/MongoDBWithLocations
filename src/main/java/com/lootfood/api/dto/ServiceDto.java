package com.lootfood.api.dto;

import com.lootfood.db.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private String id;
    private String companyId;
    private String name;
    private GeoJsonPoint location;
    private List<Delivery> deliveries;
}
