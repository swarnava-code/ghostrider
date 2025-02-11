package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class InvalidUserIdException extends RuntimeException {
    private String data;

    public InvalidUserIdException(String message, String data) {
        super(message);
        this.data = data;
    }
}
