package com.communa.server.repository;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ResidentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ResidentRepository extends CrudRepository<ResidentEntity, Long> {

    Optional<ResidentEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE ResidentEntity R SET R.community.id = :cId WHERE R.id = :rId")
    Integer updateCommunity(@Param("rId") Long residentId, @Param("cId") Long communityId);

    @Transactional
    @Modifying
    @Query("UPDATE ResidentEntity R SET R.community.id = NULL WHERE R.community.id = :rId")
    void leaveCommunity(@Param("rId") Long residentId);
}
