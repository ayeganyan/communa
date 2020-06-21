package com.communa.server.repository;

import com.communa.server.entity.ResidentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResidentRepository extends CrudRepository<ResidentEntity, Long> {
    Optional<ResidentEntity> findByEmail(String email);
}
