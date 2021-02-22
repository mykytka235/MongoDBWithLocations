package com.lootfood.repository.lootpoint;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lootfood.entity.LootPoint;
import com.lootfood.converter.JsonDateTimeConverter;
import com.lootfood.converter.JsonIdConverter;
import com.mongodb.client.model.Filters;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

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
                .forEach(document -> lootPointsInPolygon.add(convertDoc(document)));

        return new PageImpl<>(lootPointsInPolygon, pageable, lootPointsInPolygon.size());
    }

    private LootPoint convertDoc(Document document) {
        Gson gson = new Gson();
        String json = document.toJson(JsonWriterSettings.builder()
                .objectIdConverter(new JsonIdConverter())
                .dateTimeConverter(new JsonDateTimeConverter()).build())
                .replace("_id", "id");
        Type collectionType = new TypeToken<List<Double>>() {}.getType();
        List<Double> coordsInDouble = gson.fromJson(
                JsonParser.parseString(json)
                        .getAsJsonObject()
                        .getAsJsonObject("location")
                        .getAsJsonArray("coordinates"),
                collectionType
        );
        LootPoint lootPoint = gson.fromJson(json, LootPoint.class);
        lootPoint.setLocation(new GeoJsonPoint(coordsInDouble.get(0), coordsInDouble.get(1)));

        return lootPoint;
    }
}
