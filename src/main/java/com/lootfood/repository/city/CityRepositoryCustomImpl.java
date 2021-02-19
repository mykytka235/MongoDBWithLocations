package com.lootfood.repository.city;

import com.lootfood.entity.City;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class CityRepositoryCustomImpl implements CityRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public City findCityByGeoIntersects(Point point) {
        FindIterable<Document> result = mongoTemplate.getCollection("city")
                .find(Filters.geoIntersects("location", point));
        return mongoTemplate.findById(result.first().get("_id"), City.class);
    }
}
