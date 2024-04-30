package org.javaacademy.bitcoin_to_rub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleIntegrationException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("На сайте наблюдаются проблемы, приходите позже");
    }

}
