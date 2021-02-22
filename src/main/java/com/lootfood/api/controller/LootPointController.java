package com.lootfood.api.controller;

import com.lootfood.api.dto.LootPointDto;
import com.lootfood.entity.LootPoint;
import com.lootfood.service.LootPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<LootPointDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return lootPointService.getAll(PageRequest.of(page, size)).map(lootPoint -> transform(lootPoint));
    }

    @GetMapping(value = "/all/in", params = { "page", "size" })
    public Page<LootPointDto> getAll(@RequestBody List<List<Double>> points, @RequestParam("page") int page, @RequestParam("size") int size) {
        return lootPointService.getAllInPolygon(points ,PageRequest.of(page, size)).map(lootPoint -> transform(lootPoint));
    }
}