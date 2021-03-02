package com.lootfood.db.repository.custom;

import com.lootfood.db.entity.User;

import java.lang.reflect.InvocationTargetException;

public interface UserRepositoryCustom {
    User update(User user) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
