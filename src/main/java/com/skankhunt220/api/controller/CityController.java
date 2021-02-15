package com.skankhunt220.api.controller;

import java.util.List;

import com.skankhunt220.api.transformer.CityTransformer;
import com.mongodb.client.model.geojson.Position;
import com.skankhunt220.entity.City;
import org.bson.Document;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skankhunt220.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @PostMapping("/several")
    public List<City> addCities(@RequestBody List<City> cities) {
        return cityService.addCities(cities);
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable("id") String id) {
        return cityService.getById(id);
    }

    @GetMapping("/{name}")
    public City getByName(@PathVariable("name") String name) {
        return cityService.getByName(name);
    }

    @GetMapping
    public Document getByLocation(@RequestBody Point point) {
        return cityService.getByLocation(new com.mongodb.client.model.geojson.Point(new Position(point.getX(), point.getY())));
    }

    @GetMapping("/allCities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    public City update(@PathVariable("id") String id, @RequestBody City city) {
        return cityService.updateCity(CityTransformer.transform(id, city));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        cityService.delete(id);
    }
}