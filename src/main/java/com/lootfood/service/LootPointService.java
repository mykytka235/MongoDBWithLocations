package com.lootfood.service;

import com.lootfood.db.entity.LootPoint;
import com.lootfood.db.repository.LootPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LootPointService {
    private final LootPointRepository lootpointRepository;

    public LootPoint add(LootPoint lootPoint) {
        return lootpointRepository.save(lootPoint);
    }

    public LootPoint getById(String id) {
        return lootpointRepository.findById(id).get();
    }

    public Page<LootPoint> getAll(Pageable pageable) {
        return lootpointRepository.findAll(pageable);
    }

    public Page<LootPoint> getAllInPolygon(List<List<Double>> points, Pageable pageable) {
        return lootpointRepository.findAllLootPointsInPolygon(points, pageable);
    }

    public Page<LootPoint> getAllWithinPoint(Point point, Pageable pageable) {
        return lootpointRepository.findAllLootPointsWithinPoint(point, pageable);
    }

    public LootPoint update(LootPoint lootPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return lootpointRepository.update(lootPoint);
    }
}
