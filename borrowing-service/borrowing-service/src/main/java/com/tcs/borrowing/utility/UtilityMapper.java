package com.tcs.borrowing.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tcs.borrowing.model.Book;


public class UtilityMapper {
    public static Book responseToModel(String json)  {
        try {
            return getMapper().readValue(json, Book.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static String getJsonString(Book json) {
        try {
            return getMapper().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
