package com.example.server.service;

import com.example.server.entity.DriverEntity;
import com.example.server.repo.DriverRepo;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepo repo;
    public DriverService(DriverRepo repo) {
        this.repo = repo;
    }
    public void save(DriverEntity driver) {
        repo.save(driver);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
