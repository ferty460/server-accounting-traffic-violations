package com.example.server.service;

import com.example.server.entity.DriverEntity;
import com.example.server.repo.DriverRepo;
import com.example.server.utils.DriverValidationUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DriverService {

    private final DriverRepo repo;
    public DriverService(DriverRepo repo) {
        this.repo = repo;
    }

    // добавление
    public DriverEntity save(DriverEntity driver) {
        DriverValidationUtils.validateDriver(driver); // валидация
        return repo.save(driver);
    }

    // удаление по id
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // все водители
    public Iterable<DriverEntity> getAll() {
        return repo.findAll();
    }

    // 8. поиск по данным паспорта (серия, номер)
    public DriverEntity getByPassport(String series, String number) {
        return repo.findByPassportSeriesAndPassportNumber(series, number);
    }

    // 7. поиск по номеру авто
    public DriverEntity getByCarNumber(String number) {
        return repo.findByCars_Number(number);
    }

    // 6. поиск всех совершивших нарушение в ук. дату
    public Iterable<DriverEntity> getAllByViolationTime(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time) {
        return repo.findByViolations_Time(time);
    }

    // 5. сумма штрафов больше ук. значения
    public Iterable<DriverEntity> getAllByViolationSumGreater(int n) {
        return repo.getAllByViolationSumGreater(n);
    }

    // 4. больше одного нарушения
    public Iterable<DriverEntity> getAllByViolationsCountGreaterOne() {
        return repo.getAllByViolationsCountGreaterOne();
    }

    // 3. список водителей с определенным нарушением
    public Iterable<DriverEntity> getAllByViolationKind(String kind) {
        return repo.findByViolations_PenaltyKind(kind);
    }

    // 2. список водителей, оплативших часть штрафа
    public Iterable<DriverEntity> findByViolationsPaidNotFully() {
        return repo.findByViolationsPaidNotFully();
    }

    // 1. список водителей, не оплативших штраф
    public Iterable<DriverEntity> getAllByPaidEqual0() {
        return repo.findByViolations_PaidEquals(0);
    }
}
