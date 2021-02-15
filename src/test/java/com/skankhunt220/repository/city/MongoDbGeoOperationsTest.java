package com.skankhunt220.repository.city;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GeolocationRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void givenNearbyLocation_whenSearchUsingIntersect_thenFound() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("cities");
        Point point = new Point(new Position(30.596923828125, 50.45750402042058));

        FindIterable<Document> result = collection.find(
                Filters.geoIntersects("location", point)
        );

        assertNotNull(result.first());
        assertEquals("Kiev", result.first().get("name"));
    }

    @Test
    public void findInSphere() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("cities");
        double distanceInRad = 125.0 / 6371;

        FindIterable<Document> result = collection.find(
                Filters.geoWithinCenterSphere("location", 30.83312, 50.3156, distanceInRad)
        );
        assertNotNull(result.first());
    }
}