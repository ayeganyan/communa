package com.communa.server.repository;

import com.communa.server.entity.Resident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResidentRepository extends CrudRepository<Resident, Long> {
    Optional<Resident> findByEmail(String email);
}
