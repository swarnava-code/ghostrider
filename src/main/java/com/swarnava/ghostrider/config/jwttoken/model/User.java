package com.swarnava.ghostrider.config.jwttoken.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String firstName;
    private String lastName;
}
