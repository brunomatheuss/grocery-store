package com.bruno.grocery_store.exceptions;

import lombok.Getter;

@Getter
public record ExceptionResponse(String message) {
}
