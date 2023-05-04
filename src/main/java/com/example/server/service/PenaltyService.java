package com.example.server.service;

import com.example.server.entity.PenaltyEntity;
import com.example.server.repo.PenaltyRepo;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {

    private final PenaltyRepo repo;
    public PenaltyService(PenaltyRepo repo) {
        this.repo = repo;
    }
    public PenaltyEntity save(PenaltyEntity penalty) {
        return repo.save(penalty);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Iterable<PenaltyEntity> getAll() {
        return repo.findAll();
    }
}