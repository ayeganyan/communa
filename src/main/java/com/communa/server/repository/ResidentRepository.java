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
    @Query("UPDATE ResidentEntity r SET r.community.id = :cId WHERE r.id = :rId")
    Integer joinCommunity(@Param("rId") Long residentId, @Param("cId") Long communityId);

    @Transactional
    @Modifying
    @Query("UPDATE ResidentEntity r SET r.community.id = NULL WHERE r.community.id = :rId")
    void leaveCommunity(@Param("rId") Long residentId);

    @Transactional
    @Modifying
    @Query("UPDATE ResidentEntity r SET r.parkingLot.id = :pId WHERE r.id = :rId")
    Integer acquireParkingLot(@Param("rId") Long residentId, @Param("pId") Long parkingLotId);

    @Transactional
    @Modifying
    @Query("UPDATE ResidentEntity r SET r.parkingLot.id = NULL WHERE r.community.id = :rId")
    void releaseParkingLot(@Param("rId") Long parkingLotId);
}
