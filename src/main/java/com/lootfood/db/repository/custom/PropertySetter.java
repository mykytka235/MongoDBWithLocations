package com.lootfood.db.repository.custom;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class PropertySetter {
    public static <T> void setFieldsToUpdate(T object, Update update) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors(object)) {
            String propertyName = descriptor.getName();
            Object propertyContent = PropertyUtils.getSimpleProperty(object, propertyName);
            if (propertyName.equals("updateDate")) {
                update.set(propertyName, new Date());
            }
            if (propertyContent == null) {
                continue;
            }
            update.set(propertyName, propertyContent);
        }
    }
}
