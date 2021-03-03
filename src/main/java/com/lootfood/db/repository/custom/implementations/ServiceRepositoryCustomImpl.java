package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.Service;
import com.lootfood.db.repository.custom.PropertySetter;
import com.lootfood.db.repository.custom.ServiceRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class ServiceRepositoryCustomImpl implements ServiceRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Service update(Service service) {
        Query query = new Query(Criteria.where("id").is(service.getId()));
        Update update = new Update();
        PropertySetter.setFieldsToUpdate(service, update);

        mongoTemplate.findAndModify(query, update, Service.class);
        return mongoTemplate.findOne(query, Service.class);
    }
}
