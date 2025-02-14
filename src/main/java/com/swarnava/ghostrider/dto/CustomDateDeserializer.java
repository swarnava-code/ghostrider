//package com.swarnava.ghostrider.dto;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class CustomDateDeserializer extends JsonDeserializer<Date> {
//    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//
//    @Override
//    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        try {
//            return formatter.parse(p.getText());
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid date format. Use dd-MM-yyyy");
//        }
//    }
//}
