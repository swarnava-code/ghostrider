package com.swarnava.ghostrider.config.jwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRes {
    private String email;
    private String token;
}
