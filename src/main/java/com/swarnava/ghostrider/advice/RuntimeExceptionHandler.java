package com.swarnava.ghostrider.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(RuntimeException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getLocalizedMessage(),
                RuntimeException.class, exception.getStackTrace()[0].toString()
        ));
    }

}
