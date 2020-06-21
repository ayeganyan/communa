package com.communa.server.controller;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ParkingLotEntity;
import com.communa.server.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
public class ParkingController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/v1/parkinglot")
    public ResponseEntity<ParkingLotEntity> createParkingLot(@RequestBody ParkingLotEntity parkingLotEntity) {
        ParkingLotEntity savedParkingLot = parkingLotService.createParkingLot(parkingLotEntity);

        return ResponseEntity
                .created(getParkingLotUri(parkingLotEntity))
                .body(savedParkingLot);
    }

    @GetMapping("/v1/parkinglot/{id}")
    public ResponseEntity<ParkingLotEntity> getCommunity(@PathVariable Long id) {
        return ResponseEntity.ok(parkingLotService.getParkingLot(id));
    }

    @GetMapping("/v1/parkinglot")
    public ResponseEntity<Set<ParkingLotEntity>> getCommunities() {
        return ResponseEntity.ok(parkingLotService.getParkingLots());
    }


    @DeleteMapping("/v2/parkinglot/{id}")
    public ResponseEntity<Object> deleteParkingLot(@PathVariable Long id) {
        parkingLotService.deleteParkingLot(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private static URI getParkingLotUri(ParkingLotEntity parkingLotEntity) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(parkingLotEntity.getId())
                .toUri();
    }
}
