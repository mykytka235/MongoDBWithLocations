package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.Order;
import com.lootfood.db.repository.custom.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Order update(Order order) {
        Query query = new Query(Criteria.where("id").is(order.getId()));
        Update update = new Update()
                .set("name", order.getName())
                .set("user", order.getUser())
                .set("service", order.getService())
                .set("description", order.getDescription())
                .set("updateDate", new Date());

        mongoTemplate.findAndModify(query, update, Order.class);
        return mongoTemplate.findById(order.getId(), Order.class);
    }
}