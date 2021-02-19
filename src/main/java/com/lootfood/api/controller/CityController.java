package com.lootfood.api.controller;

import com.lootfood.api.Dto.CityDto;
import com.lootfood.entity.City;
import com.lootfood.service.CityService;
import com.mongodb.client.model.geojson.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.lootfood.api.transformer.CityTransformer.transform;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public CityDto add(@RequestBody CityDto dto) {
        return transform(cityService.add(transform(dto)));
    }

    @GetMapping("/{id}")
    public CityDto getById(@PathVariable("id") String id) {
        return transform(cityService.getById(id));
    }

    @GetMapping("/{name}")
    public CityDto getByName(@PathVariable("name") String name) {
        return transform(cityService.getByName(name));
    }

    @GetMapping
    public CityDto getByLocation(@RequestBody Point point) {
        return transform(cityService
                .getByLocation(new com.mongodb.client.model.geojson.Point(new Position(point.getX(), point.getY()))));
    }

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<CityDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<City> cities = cityService.getAll(pageable);

        return new PageImpl<CityDto>(cities.stream().map(city -> transform(city)).collect(Collectors.toList()),
                pageable, cities.getTotalElements());
    }

    @PutMapping("/{id}")
    public CityDto update(@PathVariable("id") String id, @RequestBody CityDto dto) {
        return transform(id, cityService.update(transform(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        cityService.delete(id);
    }
}