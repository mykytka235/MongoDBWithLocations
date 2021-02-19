package com.lootfood.repository.city;

import com.lootfood.entity.City;
import com.mongodb.client.model.geojson.Point;

public interface CityRepositoryCustom {
    City findCityByGeoIntersects(Point point);
}
