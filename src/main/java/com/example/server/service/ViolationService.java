package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.repo.DriverRepo;
import com.example.server.repo.ViolationRepo;
import com.example.server.utils.ViolationValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class ViolationService {

    private final ViolationRepo repo;
    public ViolationService(ViolationRepo repo) {
        this.repo = repo;
    }

    // добавление
    public ViolationEntity save(ViolationEntity violation) {
        ViolationValidationUtils.validateViolation(violation); // валидация
        return repo.save(violation);
    }

    // удаление по id
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // все нарушения
    public Iterable<ViolationEntity> getAll() {
        return repo.findAll();
    }

    // список нарушений владельца
    public Iterable<ViolationEntity> getAllByDriver(DriverEntity driver) {
        return repo.findAllByDriver(driver);
    }
}
