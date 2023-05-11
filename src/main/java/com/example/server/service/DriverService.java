package com.example.server.service;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.exception.ValidationExceptionDriver;
import com.example.server.repo.DriverRepo;
import com.example.server.utils.DriverValidationUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Spliterator;

@Service
public class DriverService {

    private final DriverRepo repo;
    public DriverService(DriverRepo repo) {
        this.repo = repo;
    }

    // добавление
    public DriverEntity save(DriverEntity driver) {
        DriverValidationUtils.validateDriver(driver);
        return repo.save(driver);
    }

    // удаление по id
    public void delete(String n) {
        try {
            DriverValidationUtils.validateDelete(n);
            Long id = Long.parseLong(n);
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ValidationExceptionDriver("Водителя с данным id несуществует");
        }
    }

    // все водители
    public Iterable<DriverEntity> getAll() {
        Iterable<DriverEntity> drivers = repo.findAll();
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей нет");
        } else {
            return repo.findAll();
        }
    }

    // 8. поиск по данным паспорта (серия, номер)
    public DriverEntity getByPassport(String series, String number) {
        DriverValidationUtils.validatePassport(series, number);
        if (repo.findByPassportSeriesAndPassportNumber(series, number) == null) {
            throw new ValidationExceptionDriver("Водителя с такими паспортными данными нет");
        } else {
            return repo.findByPassportSeriesAndPassportNumber(series, number);
        }
    }

    // 7. поиск по номеру авто
    public DriverEntity getByCarNumber(String number) {
        DriverValidationUtils.validateCarNumber(number);
        if (repo.findByCars_Number(number) == null) {
            throw new ValidationExceptionDriver("Автомобиля с номером " + number + " нет");
        } else {
            return repo.findByCars_Number(number);
        }
    }

    // 6. поиск всех совершивших нарушение в ук. дату todo: доделать
    public Iterable<DriverEntity> getAllByViolationTime(Date date) {
        Iterable<DriverEntity> drivers = repo.findDistinctByViolations_Time(date);
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("В эту дату нарушений не замечалось");
        } else {
            return repo.findDistinctByViolations_Time(date);
        }
    }

    // 5. сумма штрафов больше ук. значения
    public Iterable<DriverEntity> getAllByViolationSumGreater(String n) {
        DriverValidationUtils.validateSum(n);
        int a = Integer.parseInt(n);
        Iterable<DriverEntity> drivers = repo.getAllByViolationSumGreater(a);
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей, чья сумма всех имеющихся у них нарушений превышает " + n + ", нет");
        } else {
            return repo.getAllByViolationSumGreater(a);
        }
    }

    // 4. больше одного нарушения
    public Iterable<DriverEntity> getAllByViolationsCountGreaterOne() {
        Iterable<DriverEntity> drivers = repo.getAllByViolationsCountGreaterOne();
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей с больше, чем одно нарушение, нет");
        } else {
            return repo.getAllByViolationsCountGreaterOne();
        }
    }

    // 3. список водителей с определенным нарушением
    public Iterable<DriverEntity> getAllByViolationKind(String kind) {
        Iterable<DriverEntity> drivers = repo.findDistinctByViolations_PenaltyKind(kind);
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей с таким нарушением нет");
        } else {
            return repo.findDistinctByViolations_PenaltyKind(kind);
        }
    }

    // 2. список водителей, оплативших часть штрафа
    public Iterable<DriverEntity> findByViolationsPaidNotFully() {
        Iterable<DriverEntity> drivers = repo.findByViolationsPaidNotFully();
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей, оплативших только часть штрафа, нет");
        } else {
            return repo.findByViolationsPaidNotFully();
        }
    }

    // 1. список водителей, не оплативших штраф
    public Iterable<DriverEntity> getAllByPaidEqual0() {
        Iterable<DriverEntity> drivers = repo.findDistinctByViolations_PaidEquals(0);
        Spliterator spliterator = drivers.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Водителей, не оплативших штраф, нет");
        } else {
            return repo.findDistinctByViolations_PaidEquals(0);
        }
    }
}
