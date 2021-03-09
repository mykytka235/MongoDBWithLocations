package com.lootfood.db.repository.custom;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

public class PropertySetter {
    public static <T> void setFieldsToUpdate(T object, Update update) {
        Arrays.asList(PropertyUtils.getPropertyDescriptors(object)).forEach(descriptor -> {
            try {
                String propertyName = descriptor.getName();
                Object propertyContent = PropertyUtils.getSimpleProperty(object, propertyName);
                if (propertyName.equals("updateDate")) update.set(propertyName, new Date());
                if (propertyContent == "null") return;

                update.set(propertyName, propertyContent);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                throw new RuntimeException();
            }
        });
    }
}
