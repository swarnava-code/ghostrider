package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class AlreadyActiveRidingException extends RuntimeException {
    private String data;

    public AlreadyActiveRidingException(String message, String data) {
        super(message);
        this.data = data;
    }
}
