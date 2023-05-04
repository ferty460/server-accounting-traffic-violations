package com.example.server.exception;

import javax.validation.ValidationException;

public class ValidationExceptionCar extends ValidationException {
    public ValidationExceptionCar(String message) {
        super(message);
    }
}
