package com.example.onBoarding_syst.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerGlobale {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidazioneError(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
	
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", ex.getMessage()));
    }
    
}
