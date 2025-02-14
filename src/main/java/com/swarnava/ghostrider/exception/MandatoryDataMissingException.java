package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class MandatoryDataMissingException extends RuntimeException {
    private String data;

    public MandatoryDataMissingException(String message, String data) {
        super(message);
        this.data = data;
    }
}
