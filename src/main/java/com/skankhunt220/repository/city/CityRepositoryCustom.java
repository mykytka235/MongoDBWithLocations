package com.skankhunt220.repository.city;

import com.mongodb.client.model.geojson.Point;
import org.bson.Document;

public interface CityRepositoryCustom {
    Document findCityByGeoIntersects(Point point);
}
