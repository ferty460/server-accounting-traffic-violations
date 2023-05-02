package com.example.server.repo;

import com.example.server.entity.ViolationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ViolationRepo extends CrudRepository<ViolationEntity, Long> {
}
