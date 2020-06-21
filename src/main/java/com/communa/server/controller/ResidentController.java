package com.communa.server.controller;

import com.communa.server.entity.ResidentEntity;
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
    public ResponseEntity<ResidentEntity> registerResident(@Valid @RequestBody ResidentEntity residentEntity) {
        ResidentEntity savedResidentEntity = residentService.registerResident(residentEntity);

        return ResponseEntity
                .created(getResidentUri(savedResidentEntity))
                .body(savedResidentEntity);

    }

    @GetMapping("/v1/resident")
    public ResponseEntity<Set<ResidentEntity>> getResident() {
        return ResponseEntity.ok(residentService.getResidents());
    }

    @GetMapping("/v1/resident/{id}")
    public ResponseEntity<ResidentEntity> getResident(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getResident(id));
    }

    @DeleteMapping("/v1/resident/{id}")
    public ResponseEntity<Object> deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/v1/resident/{id}")
    public ResponseEntity<ResidentEntity> updateResident(@PathVariable Long id, @RequestBody ResidentEntity residentEntity) {
        ResidentEntity savedResidentEntity = residentService.updateResident(id, residentEntity);

        return ResponseEntity
                .created(getResidentUri(savedResidentEntity))
                .body(savedResidentEntity);
    }

    private static URI getResidentUri(ResidentEntity residentEntity) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(residentEntity.getId())
                .toUri();
    }
}
