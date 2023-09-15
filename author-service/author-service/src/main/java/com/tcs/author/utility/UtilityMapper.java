package com.tcs.author.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tcs.author.model.Book;

import java.util.List;

public class UtilityMapper {
    public static <T> List responseToModel(String json, Class<T> clazz)  {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readerForListOf(clazz).readValue(json);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
