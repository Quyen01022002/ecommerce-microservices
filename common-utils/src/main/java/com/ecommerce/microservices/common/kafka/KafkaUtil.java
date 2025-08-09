package com.ecommerce.microservices.common.kafka;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class KafkaUtil {
    public final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }
    public static <T> Optional<T> getBodyMessage(String message, Class<T> classObj) {
        try {
            System.out.println(message);
            HashMap<String, Object> content = objectMapper.readValue(message, HashMap.class);
            if (!content.containsKey("payload")
                    || Objects.isNull(content.get("payload"))) {
                throw new IllegalArgumentException(MessageFormat.format("Kafka message does not have body attribute: {}", message));
            } else {
                return Optional.of(objectMapper.convertValue(content.get("payload"), classObj));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
    public static <T> Optional<T> getBodyMessageOJ(String message, Class<T> classObj) {
        try {
            System.out.println(message);
            HashMap<String, Object> content = objectMapper.readValue(message, HashMap.class);
            if (content.isEmpty()) {
                throw new IllegalArgumentException(MessageFormat.format("Kafka message does not have body attribute: {}", message));
            } else {
                return Optional.of(objectMapper.convertValue(content, classObj));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}