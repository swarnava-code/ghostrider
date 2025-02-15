package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class ParentUserDefinedException extends RuntimeException {
    public String data;

    public ParentUserDefinedException(String message) {
        super(message);
//        this.data = data;
    }

    public ParentUserDefinedException(String message, String data) {
        super(message);
        this.data = data;
    }
}
