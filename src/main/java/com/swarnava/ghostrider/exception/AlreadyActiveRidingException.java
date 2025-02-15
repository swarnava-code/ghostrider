package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class AlreadyActiveRidingException extends ParentUserDefinedException {

    public AlreadyActiveRidingException(String message, String data) {
        super(message, data);
        //super.data = data;
    }
}
