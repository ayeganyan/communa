package com.communa.server.service;

import com.communa.server.entity.ParkingLotEntity;
import com.communa.server.exception.DuplicateException;
import com.communa.server.exception.NotFoundException;
import com.communa.server.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository  parkingLotRepository;

    public ParkingLotEntity createParkingLot(ParkingLotEntity parkingLotEntity) {
        if(parkingLotRepository.findByCode(parkingLotEntity.getCode()).isPresent()) {
            throw new DuplicateException(format("Parking lot with name %s already exists", parkingLotEntity.getCode()));
        }

        return parkingLotRepository.save(parkingLotEntity);
    }

    public void deleteParkingLot(Long id) {
        parkingLotRepository.deleteById(id);
    }

    public ParkingLotEntity getParkingLot(Long id) {
        return parkingLotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Parking Lot with id %d not found", id)));
    }

    public Set<ParkingLotEntity> getParkingLots() {
        HashSet<ParkingLotEntity> parkingLotEntities = new HashSet<>();
        parkingLotRepository.findAll().forEach(parkingLotEntities::add);

        return parkingLotEntities;
    }
}
