package com.lootfood.repository.lootpoint;

import com.lootfood.entity.LootPoint;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
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
        FindIterable<Document> result = mongoTemplate.getCollection("lootpoint")
                .find(Filters.geoWithinPolygon("location", points));
        List<LootPoint> lootPointsInCity = new ArrayList<>();
        result.forEach(doc -> lootPointsInCity.add(mongoTemplate.findById(doc.get("_id").toString(), LootPoint.class)));

        int start = (int) pageable.getOffset();
        int end = (int) ((start + pageable.getPageSize()) > lootPointsInCity.size() ? lootPointsInCity.size() : (start + pageable.getPageSize()));
        return new PageImpl<LootPoint>(lootPointsInCity.subList(start, end), pageable, lootPointsInCity.size());
    }

    @Override
    public LootPoint update(LootPoint lootPoint) {
        Query query = new Query(Criteria.where("id").is(lootPoint.getId()));
        Update update = new Update();
        update.set("name", lootPoint.getName());
        update.set("description", lootPoint.getDescription());
        update.set("type", lootPoint.getType());
        update.set("orders", lootPoint.getOrders());
        update.set("location", lootPoint.getLocation());
        update.set("updateDate", new Date());
        mongoTemplate.findAndModify(query, update, LootPoint.class);

        return mongoTemplate.findById(lootPoint.getId(), LootPoint.class);
    }
}
