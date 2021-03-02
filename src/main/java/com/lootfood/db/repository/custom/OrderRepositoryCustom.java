package com.lootfood.db.repository.custom;

import com.lootfood.db.entity.Order;

import java.lang.reflect.InvocationTargetException;

public interface OrderRepositoryCustom {
    Order update(Order order) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}