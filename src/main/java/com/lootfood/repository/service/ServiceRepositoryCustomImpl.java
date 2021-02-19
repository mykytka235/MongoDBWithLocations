package com.lootfood.repository.service;

import com.lootfood.entity.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class ServiceRepositoryCustomImpl implements ServiceRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Override
    public Service update(Service service){
        Query query = new Query(Criteria.where("id").is(service.getId()));
        Update update = new Update();
        update.set("name", service.getName());
        update.set("updateDate", new Date());
        mongoTemplate.findAndModify(query, update, Service.class);

        return mongoTemplate.findById(service.getId(), Service.class);
    }
}
