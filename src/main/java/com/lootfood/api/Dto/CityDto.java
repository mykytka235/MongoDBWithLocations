package com.lootfood.api.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private String id;
    private String name;
    private GeoJsonPolygon location;
}
