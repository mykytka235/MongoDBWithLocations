package com.lootfood.api.controller;

import com.lootfood.api.dto.CityDto;
import com.lootfood.service.CityService;
import com.mongodb.client.model.geojson.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.lootfood.api.transformer.CityTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public CityDto create(@RequestBody CityDto dto) {
        return transform(cityService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public CityDto update(@PathVariable("id") String id, @RequestBody CityDto dto) {
        return transform(id, cityService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public CityDto getById(@PathVariable("id") String id) {
        return transform(cityService.getById(id));
    }

    @GetMapping
    public CityDto getByLocation(@RequestBody Point point) {
        return transform(cityService.getByLocation(new com.mongodb.client.model.geojson.Point(new Position(point.getX(), point.getY()))));
    }

    @GetMapping(value = "/all")
    public Page<CityDto> getAll(Integer page, Integer size) {
        return cityService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}