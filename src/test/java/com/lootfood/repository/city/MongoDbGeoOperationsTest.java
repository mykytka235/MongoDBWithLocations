package com.lootfood.repository.city;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.lootfood.entity.City;
import com.lootfood.entity.LootPoint;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootTest
class MongoDbGeoOperationsTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void givenNearbyLocation_whenSearchUsingIntersect_thenFound() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("city");
        Point point = new Point(new Position(30.596923828125, 50.45750402042058));

        FindIterable<Document> result = collection.find(
                Filters.geoIntersects("location", point)
        );

        assertNotNull(result.first());
        assertEquals("Kiev", result.first().get("name"));
    }

    @Test
    public void findInSphere() throws JSONException {
        double distanceInRad = 125.0 / 6371;

        FindIterable<Document> result = mongoTemplate.getCollection("city").find(
                Filters.geoWithinCenterSphere("location", 30.596923828125, 50.45750402042058, distanceInRad)
        );
        JSONArray arr = new JSONObject(result.first()).getJSONObject("location").getJSONArray("coordinates").getJSONArray(0);
        System.out.println(arr);
        Type collectionType = new TypeToken<List<List<Double>>>() {
        }.getType();
        Gson gson = new Gson();
        List<List<Double>> enums = gson.fromJson(arr.toString(), collectionType);
        ArrayList<Position> positions = new ArrayList<Position>();
        ArrayList<List<Double>> points = new ArrayList<List<Double>>();
        for (List<Double> point : enums) {
            positions.add(new Position(point.get(0), point.get(1)));
            points.add(Arrays.asList(point.get(0) + 1.0, point.get(1) + 1.0));
        }
        Polygon geometry = new Polygon(positions);
        System.out.println(geometry);
        FindIterable<Document> result2 = mongoTemplate.getCollection("city").find(
                Filters.geoWithinPolygon("location", points)
        );

        assertNotNull(result.first());
    }

    @Test
    public void testLoopPointSearch() {
        GeoJsonPolygon polygon = mongoTemplate.findOne(new Query(where("name").is("Kiev")), City.class).getLocation();
        MongoCollection<Document> collection = mongoTemplate.getCollection("lootpoint");
        ArrayList<List<Double>> points = new ArrayList<List<Double>>();
        for (org.springframework.data.geo.Point point : polygon.getPoints()) {
            points.add(Arrays.asList(point.getX(), point.getY()));
        }
        FindIterable<Document> result = collection.find(
                Filters.geoWithinPolygon("location", points)
        );
        List<LootPoint> lootPointsInCity = new ArrayList<>();
        result.forEach(doc -> lootPointsInCity.add(mongoTemplate.findById(doc.get("_id").toString(), LootPoint.class)));
        lootPointsInCity.forEach(lootPoint -> System.out.println(lootPoint));
        JSONObject jsonObject = new JSONObject(result.first());
        Gson gson = new GsonBuilder().setDateFormat("EEE MMM d HH:mm:ss z yyyy").create();
        LootPoint lootPoint = gson.fromJson(jsonObject.toString(), LootPoint.class);
        System.out.println(lootPoint);
        assertNotNull(result.first());
    }
}
