package com.lootfood.api.controller;

import static com.lootfood.api.transformer.CityTransformer.*;

import com.lootfood.api.Dto.CityDto;
import com.lootfood.entity.City;
import com.lootfood.service.CityService;
import com.mongodb.client.model.geojson.Position;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public CityDto add(@RequestBody CityDto city) {
        return transform(cityService.add(transform(city)));
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
    public Document getByLocation(@RequestBody Point point) {
        return cityService.getByLocation(new com.mongodb.client.model.geojson.Point(new Position(point.getX(), point.getY())));
    }

    @GetMapping(value = "/all", params = {"page", "size"})
    public Page<City> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return cityService.getAll(PageRequest.of(page, size));
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