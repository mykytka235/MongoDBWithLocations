package com.lootfood.api.controller;

import com.lootfood.api.Dto.LootPointDto;
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
@RequestMapping("/api/loot-point")
@RequiredArgsConstructor
public class LootPointController {
    private final LootPointService lootPointService;

    @PostMapping
    public LootPointDto add(@RequestBody LootPointDto dto) {
        return transform(lootPointService.add(transform(dto)));
    }

    @GetMapping("/{id}")
    public LootPointDto getById(@PathVariable("id") String id) {
        return transform(lootPointService.getById(id));
    }

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<LootPointDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LootPoint> lootPoints = lootPointService.getAll(pageable);

        return new PageImpl<LootPointDto>(
                lootPoints.stream().map(lootPoint -> transform(lootPoint)).collect(Collectors.toList()), pageable,
                lootPoints.getTotalElements());
    }

    @GetMapping(value = "/all/in/polygon", params = { "page", "size" })
    public Page<LootPointDto> getAllInPolygon(@RequestBody List<List<Double>> points, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LootPoint> lootPoints = lootPointService.getAllInPolygon(points ,pageable);

        return new PageImpl<LootPointDto>(
                lootPoints.stream().map(lootPoint -> transform(lootPoint)).collect(Collectors.toList()), pageable,
                lootPoints.getTotalElements());
    }

    @PutMapping("/{id}")
    public LootPointDto update(@PathVariable("id") String id, @RequestBody LootPointDto dto) {
        return transform(id, lootPointService.update(transform(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        lootPointService.delete(id);
    }
}