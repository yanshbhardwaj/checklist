package com.iedaas.checklist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class StringMapConverter implements AttributeConverter<Map<Object, Object>, String> {

    private static ObjectMapper mapper;

    static {
        // To avoid instantiating ObjectMapper again and again.
        mapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Map<Object, Object> data) {
        if (null == data) {
            // You may return null if you prefer that style
            return "{}";
        }

        try {
            return mapper.writeValueAsString(data);

        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<Object, Object> convertToEntityAttribute(String s) {
        if (null == s) {
            // You may return null if you prefer that style
            return new HashMap<>();
        }

        try {
            return mapper.readValue(s, new TypeReference<Map<Object, Object>>() {});

        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to map", e);
        }
    }
}
