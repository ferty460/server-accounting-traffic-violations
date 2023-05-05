package com.example.server.utils;

import com.example.server.entity.PenaltyEntity;
import com.example.server.exception.ValidationExceptionPenalty;

public class PenaltyValidationUtils {
    public static void validatePenalty(PenaltyEntity penalty) throws ValidationExceptionPenalty {
        String kind = penalty.getKind();
        if (kind == null || kind.isBlank()) {
            throw new ValidationExceptionPenalty("Поле \"Вид нарушения\" не может быть пустым");
        }

        String description = penalty.getDescription();
        if (description == null || description.isBlank()) {
            throw new ValidationExceptionPenalty("Поле \"Описание нарушения\" не может быть пустым");
        }

        int sumPenalty = penalty.getPenalty();
        if (sumPenalty < 500 || sumPenalty > 300_000) {
            throw new ValidationExceptionPenalty("Поле \"Сумма штрафа\" не может быть меньше 500 и больше 300 000");
        }
    }
}
