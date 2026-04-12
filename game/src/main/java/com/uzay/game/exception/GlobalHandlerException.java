package com.uzay.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice()
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleException(Exception ex, ServletWebRequest webRequest) {

        HashMap<String,Object> map  =  new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);

    }

    @ExceptionHandler(GameAlreadyExistException.class)
    public ResponseEntity<Map<String,Object>> handleAlreadyExistException(GameNotFoundException ex, ServletWebRequest webRequest) {
        HashMap<String,Object> map  =  new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("timestamp", LocalDateTime.now().toString());
        map.put("map", webRequest.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleGameNotFoundException(GameNotFoundException ex, ServletWebRequest webRequest) {
        HashMap<String,Object> map  =  new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.ALREADY_REPORTED.value());
        map.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(map);
    }



}
