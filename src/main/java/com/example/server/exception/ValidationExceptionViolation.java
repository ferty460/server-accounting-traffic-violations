package com.example.server.exception;

import javax.validation.ValidationException;

public class ValidationExceptionViolation extends ValidationException {
    public ValidationExceptionViolation(String message) {
        super(message);
    }
}
