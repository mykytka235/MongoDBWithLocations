package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.User;
import com.lootfood.db.repository.custom.PropertySetter;
import com.lootfood.db.repository.custom.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public User update(User user) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        PropertySetter.setFieldsToUpdate(user, update);

        mongoTemplate.findAndModify(query, update, User.class);
        return mongoTemplate.findOne(query, User.class);
    }
}