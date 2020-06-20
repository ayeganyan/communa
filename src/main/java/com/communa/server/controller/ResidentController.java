package com.communa.server.controller;

import com.communa.server.entity.Resident;
import com.communa.server.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/v1/resident")
    public ResponseEntity<Resident> registerResident(@Valid @RequestBody Resident resident) {
        Resident savedResident = residentService.registerResident(resident);

        return ResponseEntity
                .created(getResidentUri(savedResident))
                .body(savedResident);

    }

    @GetMapping("/v1/resident")
    public ResponseEntity<Set<Resident>> getResident() {
        return ResponseEntity.ok(residentService.getResidents());
    }

    @GetMapping("/v1/resident/{id}")
    public ResponseEntity<Resident> getResident(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getResident(id));
    }

    @DeleteMapping("/v1/resident/{id}")
    public ResponseEntity<Object> deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/v1/resident/{id}")
    public ResponseEntity<Resident> updateResident(@PathVariable Long id, @RequestBody Resident resident) {
        Resident savedResident = residentService.updateResident(id, resident);

        return ResponseEntity
                .created(getResidentUri(savedResident))
                .body(savedResident);
    }


    private static URI getResidentUri(Resident resident) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resident.getId())
                .toUri();
    }
}
