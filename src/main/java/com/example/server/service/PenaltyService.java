package com.example.server.service;

import com.example.server.entity.PenaltyEntity;
import com.example.server.repo.PenaltyRepo;
import com.example.server.utils.DriverValidationUtils;
import com.example.server.utils.PenaltyValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {

    private final PenaltyRepo repo;
    public PenaltyService(PenaltyRepo repo) {
        this.repo = repo;
    }

    // добавление
    public PenaltyEntity save(PenaltyEntity penalty) {
        PenaltyValidationUtils.validatePenalty(penalty); // валидация
        return repo.save(penalty);
    }

    // удаление по id
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // все штрафы
    public Iterable<PenaltyEntity> getAll() {
        return repo.findAll();
    }
}