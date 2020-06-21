package com.communa.server.repository;

import com.communa.server.entity.ParkingLotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ParkingLotRepository extends CrudRepository<ParkingLotEntity, Long> {
    Optional<Set<ParkingLotEntity>> findByCode(String code);
}
