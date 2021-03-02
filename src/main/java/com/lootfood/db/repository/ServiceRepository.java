package com.lootfood.db.repository;

import com.lootfood.db.entity.Service;
import com.lootfood.db.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String>, ServiceRepositoryCustom {
    Page<Service> findAll(Pageable pageable);
}
