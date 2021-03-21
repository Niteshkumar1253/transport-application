package com.globallogic.Transport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleProfileNotFound(UserNotFoundException exception){
        return new ResponseEntity<>("Profile Not Found", HttpStatus.NOT_FOUND);
    }
}
