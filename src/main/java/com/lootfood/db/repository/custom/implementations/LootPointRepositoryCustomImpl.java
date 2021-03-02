package com.lootfood.db.repository.custom.implementations;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lootfood.db.entity.LootPoint;
import com.lootfood.db.repository.custom.LootPointRepositoryCustom;
import com.lootfood.db.repository.custom.PropertySetter;
import com.lootfood.db.transformer.BsonTransformer;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.Position;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LootPointRepositoryCustomImpl implements LootPointRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Page<LootPoint> findAllLootPointsInPolygon(List<List<Double>> points, Pageable pageable) {
        List<LootPoint> lootPointsInPolygon =  new ArrayList<>();
        mongoTemplate.getCollection("lootpoint")
                .find(Filters.geoWithinPolygon("location", points))
                .skip((pageable.getPageNumber() > 0 ? pageable.getPageNumber() * pageable.getPageSize() : 0))
                .limit(pageable.getPageSize())
                .forEach(document -> lootPointsInPolygon.add(BsonTransformer.transformInLootPoint(document)));

        return new PageImpl<>(lootPointsInPolygon, pageable, lootPointsInPolygon.size());
    }
    @Override
    public Page<LootPoint> findAllLootPointsWithinPoint(Point point, Pageable pageable) {
        List<LootPoint> lootPointsInPolygon =  new ArrayList<>();

        FindIterable<Document> result = mongoTemplate.getCollection("city")
                .find(Filters.geoIntersects("location", new com.mongodb.client.model.geojson.Point(
                        new Position(point.getX(), point.getY())))
                );
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<ArrayList<Double>>>() {}.getType();
        List<List<Double>> points = gson.fromJson(
                JsonParser.parseString(result.first().toJson())
                        .getAsJsonObject()
                        .getAsJsonObject("location")
                        .getAsJsonArray("coordinates").get(0),
                collectionType
        );

        mongoTemplate.getCollection("lootpoint")
                .find(Filters.geoWithinPolygon("location", points))
                .skip((pageable.getPageNumber() > 0 ? pageable.getPageNumber() * pageable.getPageSize() : 0))
                .limit(pageable.getPageSize())
                .forEach(document -> lootPointsInPolygon.add(BsonTransformer.transformInLootPoint(document)));

        return new PageImpl<>(lootPointsInPolygon, pageable, lootPointsInPolygon.size());
    }
    @Override
    public LootPoint update(LootPoint lootPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Query query = new Query(Criteria.where("id").is(lootPoint.getId()));
        Update update = new Update();
        PropertySetter.setFieldsToUpdate(lootPoint, update);

        mongoTemplate.findAndModify(query, update, LootPoint.class);
        return mongoTemplate.findOne(query, LootPoint.class);
    }
}
