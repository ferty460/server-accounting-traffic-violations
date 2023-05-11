package com.example.server.utils;

import com.example.server.entity.DriverEntity;
import com.example.server.exception.ValidationExceptionDriver;
import com.example.server.repo.DriverRepo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DriverValidationUtils {
    public static void validateDriver(DriverEntity driver) throws ValidationExceptionDriver {
        String id = driver.getDriver_Id().toString();
        if (!id.matches("[0-9]+")) {
            throw new ValidationExceptionDriver("Не соответствует формату id");
        }

        String fullName = driver.getFullName();
        if (fullName == null || fullName.isBlank() || !(fullName.matches("[А-Я][а-я]{3,15}\s[А-Я][а-я]{3,15}\s[А-Я][а-я]{3,19}"))) {
            throw new ValidationExceptionDriver("Поле \"ФИО\" не соответствует формату (Иванов Иван Иванович)");
        }

        String series = driver.getPassportSeries();
        if (series == null || series.isBlank() || !(series.matches("[0-9]{4}"))) {
            throw new ValidationExceptionDriver("Поле \"Серия паспорта\" не соответствует формату (1234)");
        }

        String number = driver.getPassportNumber();
        if (number == null || number.isBlank() || !(number.matches("[0-9]{6}"))) {
            throw new ValidationExceptionDriver("Поле \"Номер паспорта\" не соответствует формату(123456)");
        }

        Date birthday = driver.getBirthday();
        Date today = Calendar.getInstance().getTime();
        Calendar max = new GregorianCalendar(1910, 0,1);
        if (birthday == null) {
            throw new ValidationExceptionDriver("Поле \"Дата рождения\" не соответствует формату (2023-05-05)");
        } else if (birthday.after(today) || birthday.before(max.getTime())) {
            throw new ValidationExceptionDriver("Не раньше сегодняшнего дня и не позже 01.01.1910");
        }
    }

    public static void validatePassport(String series, String number) {
        if (series == null || series.isBlank() || !(series.matches("[0-9]{4}"))) {
            throw new ValidationExceptionDriver("Поле Серия паспорта не соответствует формату (1234)");
        }
        if (number == null || number.isBlank() || !(number.matches("[0-9]{6}"))) {
            throw new ValidationExceptionDriver("Поле Номер паспорта не соответствует формату (123456)");
        }
    }

    public static void validateCarNumber(String number) {
        if (number == null || number.isBlank() || !(number.matches("[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2}"))) {
            throw new ValidationExceptionDriver("Поле Номер авто не соответствует формату (А111АА)");
        }
    }

    public static void validateDate(String time) {
        if (time == null || !(time.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"))) {
            throw new ValidationExceptionDriver("Поле time не соответствует формату (2023-05-05 03:00:00)");
        }
    }

    public static void validateSum(String sum) {
        if (sum == null || !(sum.matches("[0-9]{2,10}"))) {
            throw new ValidationExceptionDriver("Не соответствует формату");
        }
    }

    public static void validateDelete(String id) {
        if (!id.matches("[0-9]+")) {
            throw new ValidationExceptionDriver("Не соответствует формату id");
        }
    }
}
