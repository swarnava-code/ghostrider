package com.swarnava.ghostrider.config.jwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorRes {
    HttpStatus httpStatus;
    String message;
}
