package com.lootfood.db.repository.custom.implementations;

import com.lootfood.db.entity.User;
import com.lootfood.db.repository.custom.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public User update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update()
                .set("firstName", user.getFirstName())
                .set("lastName", user.getLastName())
                .set("phone", user.getPhone())
                .set("updateDate", new Date());

        mongoTemplate.findAndModify(query, update, User.class);
        return mongoTemplate.findOne(query, User.class);
    }
}