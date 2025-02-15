package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class InvalidUserIdException extends ParentUserDefinedException {

    public InvalidUserIdException(String message, String data) {
        super(message);
        this.data = data;
    }
}
