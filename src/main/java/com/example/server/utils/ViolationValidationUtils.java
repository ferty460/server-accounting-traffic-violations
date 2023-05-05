package com.example.server.utils;

import com.example.server.entity.ViolationEntity;
import com.example.server.exception.ValidationExceptionViolation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViolationValidationUtils {
    public static void validateViolation(ViolationEntity violation) throws ValidationExceptionViolation {
        Date when = violation.getTime();
        Date today = Calendar.getInstance().getTime();
        if (when.after(today)) {
            throw new ValidationExceptionViolation("Нарушение не может быть совершено позже сегодняшнего дня");
        }

        int paid = violation.getPaid();
        if (paid < 0 || paid > 300_000) {
            throw new ValidationExceptionViolation("Не меньше 0 и не больше 300 000");
        }
    }
}
