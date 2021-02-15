package com.skankhunt220.service;

import com.mongodb.client.model.geojson.Point;
import com.skankhunt220.entity.City;
import com.skankhunt220.repository.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
	private final CityRepository cityRepository;

	public City addCity(City city) {
		return cityRepository.save(city);
	}

	public List<City> addCities(List<City> cities) {
		return cityRepository.saveAll(cities);
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

	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	public City updateCity(City city) {
		return  cityRepository.save(city);
	}

	public void delete(String cityId) {
		cityRepository.deleteById(cityId);
	}
}