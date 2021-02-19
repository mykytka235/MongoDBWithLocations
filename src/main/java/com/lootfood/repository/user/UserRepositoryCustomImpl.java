package com.lootfood.repository.user;

import com.lootfood.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Override
    public User update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("firstName", user.getFirstName());
        update.set("lastName", user.getLastName());
        update.set("phone", user.getPhone());
        update.set("updateDate", new Date());
        mongoTemplate.findAndModify(query, update, User.class);

        return mongoTemplate.findById(user.getId(), User.class);
    }
}
