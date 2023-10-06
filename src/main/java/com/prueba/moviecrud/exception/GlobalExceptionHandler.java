package com.prueba.moviecrud.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> generateException(ResponseStatusException re) {
        ErrorDTO error = new ErrorDTO(
            new Date().toString(), 
            String.valueOf(re.getStatusCode().value()),
            re.getMessage());

        return new ResponseEntity<ErrorDTO>(error, re.getStatusCode());

    }

}
