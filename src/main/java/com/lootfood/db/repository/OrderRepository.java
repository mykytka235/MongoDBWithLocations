package com.lootfood.db.repository;

import com.lootfood.db.entity.Order;
import com.lootfood.db.repository.custom.OrderRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {
    Page<Order> findAll(Pageable pageable);
}
