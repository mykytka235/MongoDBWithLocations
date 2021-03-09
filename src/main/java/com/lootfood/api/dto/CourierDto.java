package com.lootfood.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierDto {
    private String id;
    private String phone;
    private String firstName;
    private String lastName;
    private GeoJsonPoint location;
}
