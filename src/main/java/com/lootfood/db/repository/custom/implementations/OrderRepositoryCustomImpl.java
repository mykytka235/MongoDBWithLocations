package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.Order;
import com.lootfood.db.repository.custom.OrderRepositoryCustom;
import com.lootfood.db.repository.custom.PropertySetter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Order update(Order order) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Query query = new Query(Criteria.where("id").is(order.getId()));
        Update update = new Update();
        PropertySetter.setFieldsToUpdate(order, update);

        mongoTemplate.findAndModify(query, update, Order.class);
        return mongoTemplate.findById(order.getId(), Order.class);
    }
}