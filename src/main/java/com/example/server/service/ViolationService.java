package com.example.server.service;

import com.example.server.entity.CarEntity;
import com.example.server.entity.DriverEntity;
import com.example.server.entity.ViolationEntity;
import com.example.server.repo.DriverRepo;
import com.example.server.repo.ViolationRepo;
import org.springframework.stereotype.Service;

@Service
public class ViolationService {

    private final ViolationRepo repo;
    public ViolationService(ViolationRepo repo) {
        this.repo = repo;
    }
    public ViolationEntity save(ViolationEntity violation) {
        return repo.save(violation);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Iterable<ViolationEntity> getAll() {
        return repo.findAll();
    }
}
