package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class RiderNotAvailableException extends RuntimeException {
    private String data;

    public RiderNotAvailableException(String message, String data) {
        super(message);
        this.data = data;
    }
}
