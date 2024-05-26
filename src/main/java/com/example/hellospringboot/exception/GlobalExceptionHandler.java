package com.example.hellospringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hellospringboot.model.response.ResponseApi;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseApi<?>> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ResponseApi<>(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<?>> handleException(Exception ex) {
        return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
