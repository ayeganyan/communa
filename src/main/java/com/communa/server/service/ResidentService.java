package com.communa.server.service;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ParkingLotEntity;
import com.communa.server.exception.DuplicateException;
import com.communa.server.exception.NotFoundException;
import com.communa.server.repository.CommunityRepository;
import com.communa.server.repository.ParkingLotRepository;
import com.communa.server.repository.ResidentRepository;
import com.communa.server.entity.ResidentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ResidentEntity registerResident(ResidentEntity residentEntity) {
        if(residentRepository.findByEmail(residentEntity.getEmail()).isPresent()){
            throw new DuplicateException(format("Resident with email %s already exist", residentEntity.getEmail()));
        }

        return residentRepository.save(residentEntity);
    }

    public ResidentEntity updateResident(Long id, ResidentEntity residentEntity) {
        if(!residentRepository.existsById(id)) {
            throw new NotFoundException(format("Resident with id %d not found", id));
        }
        residentEntity.setId(id);

        return residentRepository.save(residentEntity);
    }

    public ResidentEntity getResident(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Resident with id %d not found", id)));
    }

    public Set<ResidentEntity> getResidents() {
        HashSet<ResidentEntity> residentEntries = new HashSet<>();
        residentRepository.findAll().forEach(residentEntries::add);

        return residentEntries;
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
    }

    public ResidentEntity joinCommunity(Long residentId, Long communityId) {

        ResidentEntity residentEntity = residentRepository.findById(residentId).orElseThrow(
                () -> new NotFoundException(format("Resident with id %d not found", residentId))
        );

        CommunityEntity communityEntity = communityRepository.findById(communityId).orElseThrow(
                () -> new NotFoundException(format("Community with id %d not found", communityId))
        );
        residentEntity.setCommunity(communityEntity);
        return residentRepository.save(residentEntity);

        // The method below does not work properly that's why I am using non effective method
        // residentRepository.joinCommunity(residentId, communityId);
    }

    public void leaveCommunity(Long residentId) {
        residentRepository.leaveCommunity(residentId);

        ResidentEntity residentEntity = residentRepository.findById(residentId).orElseThrow(
                () -> new NotFoundException(format("Resident with id %d not found", residentId))
        );
        residentEntity.setCommunity(null);
        residentRepository.save(residentEntity);
    }

    public ResidentEntity acquireParkingLot(Long residentId, Long parkingLotId) {
        ResidentEntity residentEntity = residentRepository.findById(residentId).orElseThrow(
                () -> new NotFoundException(format("Resident with id %d not found", residentId))
        );
        ParkingLotEntity parkingLotEntity = parkingLotRepository.findById(parkingLotId).orElseThrow(
                () -> new NotFoundException(format("Parking lot with id %d not found", parkingLotId))
        );

        residentEntity.setParkingLot(parkingLotEntity);
        return residentRepository.save(residentEntity);

        //residentRepository.acquireParkingLot(residentId, parkingLotId);
    }

    public void releaseParkingLot(Long residentId) {
        ResidentEntity residentEntity = residentRepository.findById(residentId).orElseThrow(
                () -> new NotFoundException(format("Resident with id %d not found", residentId))
        );
        residentEntity.setParkingLot(null);

        residentRepository.save(residentEntity);
        //residentRepository.releaseParkingLot(residentId);
    }
}
