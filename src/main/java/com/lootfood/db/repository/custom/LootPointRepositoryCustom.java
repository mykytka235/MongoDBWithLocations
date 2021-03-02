package com.lootfood.db.repository.custom;

import com.lootfood.db.entity.LootPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface LootPointRepositoryCustom {
    Page<LootPoint> findAllLootPointsInPolygon(List<List<Double>> points, Pageable pageable);
    Page<LootPoint> findAllLootPointsWithinPoint(Point point, Pageable pageable);
    LootPoint update(LootPoint lootPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
