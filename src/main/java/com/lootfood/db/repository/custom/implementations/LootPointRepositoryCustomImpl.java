package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.LootPoint;
import com.lootfood.db.entity.Order;
import com.lootfood.db.repository.custom.LootPointRepositoryCustom;
import com.lootfood.db.transformer.BsonTransformer;
import com.mongodb.client.model.Filters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
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
    public LootPoint update(LootPoint lootPoint) {
        Query query = new Query(Criteria.where("id").is(lootPoint.getId()));
        Update update = new Update()
                .set("name", lootPoint.getName())
                .set("description", lootPoint.getDescription())
                .set("type", lootPoint.getType())
                .set("orders", lootPoint.getOrders())
                .set("location", lootPoint.getLocation())
                .set("updateDate", new Date());

        mongoTemplate.findAndModify(query, update, LootPoint.class);
        return mongoTemplate.findOne(query, LootPoint.class);
    }
}
