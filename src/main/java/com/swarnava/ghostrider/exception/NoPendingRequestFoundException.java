package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class NoPendingRequestFoundException extends RuntimeException {
    private String data;

    public NoPendingRequestFoundException(String message, String data) {
        super(message);
        this.data = data;
    }
}
