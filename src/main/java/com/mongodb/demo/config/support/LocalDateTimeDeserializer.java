package com.mongodb.demo.config.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeDeserializer.class);

    protected static final DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static final DateTimeFormatter dateTimeWithHmformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        logger.info(" -------------------------- deserialization localDateTime ---------------------- ");
        String str = jp.getText().trim();
        if (str.length() == 0)
            return null;
        try{
            return LocalDateTime.parse(str, dateTimeformatter);
        }catch (DateTimeParseException e){
            return LocalDateTime.parse(str,dateTimeWithHmformatter);
        }
    }

}
