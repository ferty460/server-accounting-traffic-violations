package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.repo.DriverRepo;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepo repo;
    public DriverService(DriverRepo repo) {
        this.repo = repo;
    }
    public DriverEntity save(DriverEntity driver) {
        return repo.save(driver);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Iterable<DriverEntity> getAll() {
        return repo.findAll();
    }
}
