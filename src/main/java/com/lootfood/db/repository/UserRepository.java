package com.lootfood.db.repository;

import com.lootfood.db.entity.User;
import com.lootfood.db.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    Page<User> findAll(Pageable pageable);
}