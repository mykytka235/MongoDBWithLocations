package com.lootfood.converter;

import org.bson.json.Converter;
import org.bson.json.StrictJsonWriter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonIdConverter implements Converter<ObjectId> {
    Logger logger = LoggerFactory.getLogger(JsonIdConverter.class);

    @Override
    public void convert(ObjectId value, StrictJsonWriter writer) {
        try {
            writer.writeString(value.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}