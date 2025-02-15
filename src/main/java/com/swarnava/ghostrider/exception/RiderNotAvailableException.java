package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class RiderNotAvailableException extends ParentUserDefinedException {

    public RiderNotAvailableException(String message, String data) {
        super(message);
        this.data = data;
    }
}
