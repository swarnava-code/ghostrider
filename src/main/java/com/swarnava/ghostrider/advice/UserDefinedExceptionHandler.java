package com.swarnava.ghostrider.advice;

import com.swarnava.ghostrider.exception.AlreadyActiveRidingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserDefinedExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(UserDefinedExceptionHandler.class);

    @ExceptionHandler(AlreadyActiveRidingException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(AlreadyActiveRidingException exception) {
        logger.warn(exception.getMessage());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getData(),
                AlreadyActiveRidingException.class, exception.getStackTrace()[0].toString()
        ));
    }
}
