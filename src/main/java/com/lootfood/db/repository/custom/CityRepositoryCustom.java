package com.lootfood.db.repository.custom;

import com.lootfood.db.entity.City;
import com.lootfood.db.entity.Order;
import com.mongodb.client.model.geojson.Point;

import java.lang.reflect.InvocationTargetException;

public interface CityRepositoryCustom {
    City findCityByGeoIntersects(Point point);
    City update(City city) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
