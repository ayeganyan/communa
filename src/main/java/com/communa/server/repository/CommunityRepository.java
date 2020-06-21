package com.communa.server.repository;

import com.communa.server.entity.CommunityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends CrudRepository<CommunityEntity, Long> {
    Optional<List<CommunityEntity>> findByName(String name);
}
