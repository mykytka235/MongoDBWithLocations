package com.lootfood.repository.order;

import com.lootfood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {
    Page<Order> findAll(Pageable pageable);
}
