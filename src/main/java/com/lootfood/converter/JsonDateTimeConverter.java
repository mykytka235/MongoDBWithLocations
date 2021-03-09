package com.lootfood.converter;

import org.bson.json.Converter;
import org.bson.json.StrictJsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonDateTimeConverter implements Converter<Long> {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ISO_INSTANT
            .withZone(ZoneId.of("UTC"));
    private Logger logger = LoggerFactory.getLogger(JsonDateTimeConverter.class);

    @Override
    public void convert(Long value, StrictJsonWriter writer) {
        try {
            writer.writeString(DATE_TIME_FORMATTER.format(new Date(value).toInstant()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}