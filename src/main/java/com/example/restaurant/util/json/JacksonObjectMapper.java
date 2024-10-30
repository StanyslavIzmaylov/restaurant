package com.example.restaurant.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
public class JacksonObjectMapper extends ObjectMapper {

        private static final ObjectMapper MAPPER = new JacksonObjectMapper();

        private JacksonObjectMapper() {
            registerModule(new Hibernate6Module());

            registerModule(new JavaTimeModule());
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        public static ObjectMapper getMapper() {
            return MAPPER;
        }
    }

