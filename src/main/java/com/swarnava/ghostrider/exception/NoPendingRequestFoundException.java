package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class NoPendingRequestFoundException extends ParentUserDefinedException {

    public NoPendingRequestFoundException(String message, String data) {
        super(message);
        this.data = data;
    }
}
