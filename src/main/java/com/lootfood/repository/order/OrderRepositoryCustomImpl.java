package com.lootfood.repository.order;

import com.lootfood.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Override
    public Order update(Order order) {
        Query query = new Query(Criteria.where("id").is(order.getId()));
        Update update = new Update();
        update.set("name", order.getName());
        update.set("user", order.getUser());
        update.set("service", order.getService());
        update.set("description", order.getDescription());
        update.set("updateDate", new Date());
        mongoTemplate.findAndModify(query, update, Order.class);

        return mongoTemplate.findById(order.getId(), Order.class);
    }
}
