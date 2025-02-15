package com.swarnava.ghostrider.advice;

import com.swarnava.ghostrider.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class UserDefinedExceptionHandler {

    /**
     * common utility message template
     * @param exception
     * @return
     */
    private ResponseEntity<MessageForUserDefinedException> handleException(ParentUserDefinedException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getData(),
                exception.getClass(), exception.getStackTrace()[0].toString()
        ));
    }

    @ExceptionHandler(AlreadyActiveRidingException.class)
    public ResponseEntity<MessageForUserDefinedException> handleAlreadyActiveRidingException(AlreadyActiveRidingException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return handleException(exception);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<MessageForUserDefinedException> handleInvalidUserIdException(InvalidUserIdException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return handleException(exception);
    }

    @ExceptionHandler(RiderNotAvailableException.class)
    public ResponseEntity<MessageForUserDefinedException> handleRiderNotAvailableException(RiderNotAvailableException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return handleException(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageForUserDefinedException> handleConstraintViolationException(ConstraintViolationException exception) {
        log.warn(exception.getMessage(), " cause: {}", exception.getCause());
        return handleException(exception);
    }

    @ExceptionHandler(MandatoryDataMissingException.class)
    public ResponseEntity<MessageForUserDefinedException> handleMandatoryDataMissingException(MandatoryDataMissingException exception) {
        log.warn(exception.getMessage(), " DATA: {}", exception.getData());
        return handleException(exception);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<MessageForUserDefinedException> handlePSQLException(PSQLException exception) {
        log.warn(exception.getMessage(), " ServerErrorMessage: {}", exception.getServerErrorMessage());
        return handleException(exception);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageForUserDefinedException> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.warn(exception.getMessage(), " cause: {}", exception.getCause());
        return handleException(exception);
    }



    // TODO - Add more exception handler above the following method
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Last option to handle
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageForUserDefinedException> handleException(RuntimeException exception) {
        log.error(exception.getMessage(), " DATA: {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getLocalizedMessage(),
                exception.getClass(), exception.getStackTrace()[0].toString()
        ));
    }

    /**
     * Outside Java Runtime Exception, but runtime exception
     * @example PSQLException,
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageForUserDefinedException> handleException(Exception exception) {
        log.error(exception.getMessage(), " DATA: {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(new MessageForUserDefinedException(
                exception.getMessage(), exception.getLocalizedMessage(),
                exception.getClass(), exception.getStackTrace()[0].toString()
        ));
    }

}
