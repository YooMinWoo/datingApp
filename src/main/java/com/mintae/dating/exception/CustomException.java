package com.mintae.dating.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }
}
