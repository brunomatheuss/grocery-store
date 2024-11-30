package com.bruno.grocery_store.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericErrorException extends RuntimeException {

    private final HttpStatus statusCode;

    public GenericErrorException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
