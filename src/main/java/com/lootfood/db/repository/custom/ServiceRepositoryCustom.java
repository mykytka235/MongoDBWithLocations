package com.lootfood.db.repository.custom;

import com.lootfood.db.entity.Service;

import java.lang.reflect.InvocationTargetException;

public interface ServiceRepositoryCustom {
    Service update(Service service) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
