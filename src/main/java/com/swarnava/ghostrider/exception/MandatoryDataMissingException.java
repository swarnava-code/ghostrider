package com.swarnava.ghostrider.exception;

import lombok.Getter;

@Getter
public class MandatoryDataMissingException extends ParentUserDefinedException {

    public MandatoryDataMissingException(String message, String data) {
        super(message);
        this.data = data;
    }
}
