package com.onezero.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.onezero.core.constant.PatternConst;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

public abstract class JacksonUtil {
    private static final ObjectMapper CAMEL_OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectMapper SNAKE_OBJECT_MAPPER = new ObjectMapper();


    static {
        //snake_case
        SNAKE_OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        //camel_case
        CAMEL_OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        withDefaults(CAMEL_OBJECT_MAPPER);
        withDefaults(SNAKE_OBJECT_MAPPER);

    }

    public static void withDefaults(ObjectMapper om) {
        //common config
        om.setLocale(Locale.CHINA);

        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(PatternConst.DATE_TIME_FORMATTER);
        LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(PatternConst.DATE_TIME_FORMATTER);
        LocalDateDeserializer dateDeserializer = new LocalDateDeserializer(PatternConst.DATE_FORMATTER);
        LocalDateSerializer dateSerializer = new LocalDateSerializer(PatternConst.DATE_FORMATTER);
        module.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        module.addSerializer(LocalDateTime.class, dateTimeSerializer);
        module.addDeserializer(LocalDate.class, dateDeserializer);
        module.addSerializer(LocalDate.class, dateSerializer);
        om.registerModule(module);
        //unknown fields ignore
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    }

    public static ObjectMapper newInstance() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        withDefaults(om);
        return om;
    }

    public static String asString(Object value) {
        try {
            return CAMEL_OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static String asSnakeString(Object value) {
        try {
            return SNAKE_OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readValue(String content, Class<T> clazz) {
        try {
            return CAMEL_OBJECT_MAPPER.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return CAMEL_OBJECT_MAPPER.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T readSnakeValue(String content, Class<T> clazz) {
        try {
            return SNAKE_OBJECT_MAPPER.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readSnakeValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return SNAKE_OBJECT_MAPPER.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convert(Object val, Class<T> clazz) {
        return CAMEL_OBJECT_MAPPER.convertValue(val, clazz);
    }

    public static <T> T convert(Object val, TypeReference<T> valueTypeRef) {
        return CAMEL_OBJECT_MAPPER.convertValue(val, valueTypeRef);
    }
    public static <T> T convertSnake(Object val, Class<T> clazz) {
        return SNAKE_OBJECT_MAPPER.convertValue(val, clazz);
    }

    public static <T> T convertSnake(Object val, TypeReference<T> valueTypeRef) {
        return SNAKE_OBJECT_MAPPER.convertValue(val, valueTypeRef);
    }

}
