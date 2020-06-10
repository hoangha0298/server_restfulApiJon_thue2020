package com.example.thue2020.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConvertJson {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object value) {
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    public static <T> T fromJson(String jsonInString, Class<T> valueType) {
        try {
            return mapper.readValue(jsonInString, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
