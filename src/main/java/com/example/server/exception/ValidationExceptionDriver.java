package com.example.server.exception;

import javax.validation.ValidationException;

public class ValidationExceptionDriver extends ValidationException {
    public ValidationExceptionDriver(String message) {
        super(message);
    }
}
