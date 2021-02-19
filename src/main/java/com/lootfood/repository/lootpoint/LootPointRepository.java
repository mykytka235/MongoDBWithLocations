package com.lootfood.repository.lootpoint;

import com.lootfood.entity.LootPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LootPointRepository extends MongoRepository<LootPoint, String>, LootPointRepositoryCustom {
    Page<LootPoint> findAll(Pageable pageable);
}
