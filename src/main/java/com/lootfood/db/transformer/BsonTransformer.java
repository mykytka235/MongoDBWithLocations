package com.lootfood.db.transformer;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lootfood.converter.JsonDateTimeConverter;
import com.lootfood.converter.JsonIdConverter;
import com.lootfood.db.entity.City;
import com.lootfood.db.entity.LootPoint;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BsonTransformer {
    public static LootPoint transformInLootPoint(Document document){
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

    public static City transformInCity(Document document){
        Gson gson = new Gson();
        String json = document.toJson(JsonWriterSettings.builder()
                .objectIdConverter(new JsonIdConverter())
                .dateTimeConverter(new JsonDateTimeConverter())
                .build());
        Type collectionType = new TypeToken<ArrayList<ArrayList<Double>>>() {}.getType();
        List<List<Double>> pointsList = gson.fromJson(
                JsonParser.parseString(json)
                        .getAsJsonObject()
                        .getAsJsonObject("location")
                        .getAsJsonArray("coordinates").get(0),
                collectionType
        );
        List<Point> points = new ArrayList<>();
        pointsList.forEach(doubles -> points.add(new Point(doubles.get(0), doubles.get(1))));

        return City.builder()
                .id(document.get("_id").toString())
                .name(document.get("name").toString())
                .location(new GeoJsonPolygon(points))
                .createdDate(gson.fromJson(JsonParser.parseString(json).getAsJsonObject().getAsJsonPrimitive("createdDate"), Date.class))
                .updateDate(gson.fromJson(JsonParser.parseString(json).getAsJsonObject().getAsJsonPrimitive("updateDate"), Date.class))
                .build();
    }
}
