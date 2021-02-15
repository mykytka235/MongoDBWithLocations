package com.lootfood.service;

import com.mongodb.client.model.geojson.Point;
import com.lootfood.entity.City;
import com.lootfood.repository.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
	private final CityRepository cityRepository;

	public City add(City city) {
		return cityRepository.save(city);
	}

	public City getById(String id) {
		return cityRepository.findById(id).get();
	}

	public City getByName(String name) {
		return cityRepository.findFirstByName(name);
	}

	public Document getByLocation(Point point) {
		return cityRepository.findCityByGeoIntersects(point);
	}

	public Page<City> getAll(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	public City update(City city) {
		return  cityRepository.save(city);
	}

	public void delete(String cityId) {
		cityRepository.deleteById(cityId);
	}
}