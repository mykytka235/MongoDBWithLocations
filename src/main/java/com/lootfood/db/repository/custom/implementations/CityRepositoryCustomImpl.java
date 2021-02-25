package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.City;
import com.lootfood.db.entity.LootPoint;
import com.lootfood.db.repository.custom.CityRepositoryCustom;
import com.lootfood.db.transformer.BsonTransformer;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class CityRepositoryCustomImpl implements CityRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public City findCityByGeoIntersects(Point point) {
        FindIterable<Document> result = mongoTemplate.getCollection("city")
                .find(Filters.geoIntersects("location", point));
        return BsonTransformer.transformInCity(result.first());
    }
    @Override
    public City update(City city){
        Query query = new Query(Criteria.where("id").is(city.getId()));
        Update update = new Update()
                .set("name", city.getName())
                .set("location", city.getLocation())
                .set("updateDate", new Date());

        mongoTemplate.findAndModify(query, update, City.class);
        return mongoTemplate.findOne(query, City.class);
    }
}
