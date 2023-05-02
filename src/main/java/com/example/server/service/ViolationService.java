package com.example.server.service;

import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.repo.DriverRepo;
import com.example.server.repo.ViolationRepo;

public class ViolationService {

    private final ViolationRepo repo;
    public ViolationService(ViolationRepo repo) {
        this.repo = repo;
    }
    public void save(ViolationEntity violation) {
        repo.save(violation);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
