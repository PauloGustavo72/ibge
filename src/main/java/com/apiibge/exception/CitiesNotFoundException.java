package com.apiibge.exception;

public class CitiesNotFoundException extends RuntimeException {
    public CitiesNotFoundException(String message) {
        super(message);
    }
}
