package com.example.server.exception;

import javax.validation.ValidationException;

public class ValidationExceptionPenalty extends ValidationException {
    public ValidationExceptionPenalty(String message) {
        super(message);
    }
}
