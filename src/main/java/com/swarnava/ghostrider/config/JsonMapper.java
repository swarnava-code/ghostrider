package com.swarnava.ghostrider.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper {
    @Autowired
    private ObjectMapper objectMapper;

    public String getJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
