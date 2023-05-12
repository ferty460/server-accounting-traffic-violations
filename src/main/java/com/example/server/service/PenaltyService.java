package com.example.server.service;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.PenaltyEntity;
import com.example.server.exception.ValidationExceptionDriver;
import com.example.server.repo.PenaltyRepo;
import com.example.server.utils.DriverValidationUtils;
import com.example.server.utils.PenaltyValidationUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.Spliterator;

@Service
public class PenaltyService {

    private final PenaltyRepo repo;
    public PenaltyService(PenaltyRepo repo) {
        this.repo = repo;
    }

    // добавление
    public PenaltyEntity save(PenaltyEntity penalty) {
        PenaltyValidationUtils.validatePenalty(penalty);
        return repo.save(penalty);
    }

    // удаление по id
    public void delete(String n) {
        try {
            DriverValidationUtils.validateDelete(n);
            Long id = Long.parseLong(n);
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ValidationExceptionDriver("Штрафа с данным id несуществует");
        }
    }

    // все штрафы
    public Iterable<PenaltyEntity> getAll() {
        Iterable<PenaltyEntity> penalties = repo.findAll();
        Spliterator spliterator = penalties.spliterator();
        if (spliterator.estimateSize() == 0) {
            throw new ValidationExceptionDriver("Штрафов нет");
        } else {
            return repo.findAll();
        }
    }
}