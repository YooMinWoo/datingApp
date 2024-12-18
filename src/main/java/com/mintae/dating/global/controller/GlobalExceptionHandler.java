package com.mintae.dating.global.controller;

import com.mintae.dating.global.dto.ApiResponse;
import com.mintae.dating.global.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.fail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.fail(e.getMessage()));
    }
}
