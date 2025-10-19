package com.smartcane.user.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleIllegal(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(Map.of("error","BAD_REQUEST","message",e.getMessage()));
    }
    @ExceptionHandler({jakarta.persistence.EntityNotFoundException.class, java.util.NoSuchElementException.class})
    public ResponseEntity<Map<String,Object>> handleNotFound(RuntimeException e){
        return ResponseEntity.status(404).body(Map.of("error","NOT_FOUND","message",e.getMessage()));
    }
}
