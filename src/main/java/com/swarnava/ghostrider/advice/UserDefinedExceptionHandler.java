package com.swarnava.ghostrider.advice;

import com.swarnava.ghostrider.exception.AlreadyActiveRidingException;
import com.swarnava.ghostrider.exception.InvalidUserIdException;
import com.swarnava.ghostrider.exception.RiderNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserDefinedExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(RuntimeException exception) {
        logger.warn(exception.getMessage(), " DATA: {}", exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getLocalizedMessage(),
                RuntimeException.class, exception.getStackTrace()[0].toString()
        ));
    }

    @ExceptionHandler(AlreadyActiveRidingException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(AlreadyActiveRidingException exception) {
        logger.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getData(),
                AlreadyActiveRidingException.class, exception.getStackTrace()[0].toString()
        ));
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(InvalidUserIdException exception) {
        logger.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getData(),
                InvalidUserIdException.class, exception.getStackTrace()[0].toString()
        ));
    }

    @ExceptionHandler(RiderNotAvailableException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(RiderNotAvailableException exception) {
        logger.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getData(),
                RiderNotAvailableException.class, exception.getStackTrace()[0].toString()
        ));
    }

}
