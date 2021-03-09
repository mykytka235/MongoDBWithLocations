package com.lootfood.api.controller;

import com.lootfood.api.dto.LootPointDto;
import com.lootfood.service.LootPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.lootfood.api.transformer.LootPointTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/loot/point")
@RequiredArgsConstructor
public class LootPointController {
    private final LootPointService lootPointService;

    @PostMapping
    public LootPointDto create(@RequestBody LootPointDto dto) {
        return transform(lootPointService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public LootPointDto update(@PathVariable("id") String id, @RequestBody LootPointDto dto) {
        return transform(id, lootPointService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public LootPointDto getById(@PathVariable("id") String id) {
        return transform(lootPointService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<LootPointDto> getAll(Integer page, Integer size) {
        return lootPointService.getAll(PageRequest.of(page, size)).map(lootPoint -> transform(lootPoint));
    }

    @GetMapping(value = "/all/in")
    public Page<LootPointDto> getAll(@RequestBody List<List<Double>> points,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size)
    {
        return lootPointService.getAllInPolygon(points ,PageRequest.of(page, size)).map(lootPoint -> transform(lootPoint));
    }

    @GetMapping(value = "/all/with")
    public Page<LootPointDto> getAll(@RequestBody Point point,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size) {
        return lootPointService.getAllWithinPoint(point, PageRequest.of(page, size)).map(lootPoint -> transform(lootPoint));
    }
}