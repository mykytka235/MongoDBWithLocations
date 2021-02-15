package com.lootfood.repository.city;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class CityRepositoryCustomImpl implements CityRepositoryCustom {
    private final MongoTemplate mongoTemplate;
    @Value("${spring.data.mongodb.collection}")
    private String collectionName;

    @Override
    public Document findCityByGeoIntersects(Point point) {
        FindIterable<Document> result = mongoTemplate.getCollection(collectionName).find(
                Filters.geoIntersects("location", point)
        );

        return result.first();
    }
}
