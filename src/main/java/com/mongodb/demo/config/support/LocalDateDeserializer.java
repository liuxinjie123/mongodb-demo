package com.mongodb.demo.config.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private Logger logger = LoggerFactory.getLogger(LocalDateDeserializer.class);

    protected  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)throws IOException, JsonProcessingException {
        logger.info(" -------------------------- deserialization localDate ---------------------- ");
        String string = jp.getText().trim();
        if(string.length() == 0)
            return null;
        return LocalDate.parse(string, formatter);
    }
}





