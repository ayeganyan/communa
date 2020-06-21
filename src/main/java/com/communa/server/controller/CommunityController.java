package com.communa.server.controller;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ResidentEntity;
import com.communa.server.service.CommunityService;
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

import java.net.URI;
import java.util.Set;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/v1/community")
    public ResponseEntity<CommunityEntity> createCommunity(@RequestBody CommunityEntity communityEntity) {
        CommunityEntity savedCommunityEntity = communityService.createCommunity(communityEntity);

        return ResponseEntity
                .created(getCommunityUri(communityEntity))
                .body(savedCommunityEntity);
    }

    @GetMapping("/v1/community/{id}")
    public ResponseEntity<CommunityEntity> getCommunity(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getCommunity(id));
    }

    @GetMapping("/v1/community")
    public ResponseEntity<Set<CommunityEntity>> getCommunities() {
        return ResponseEntity.ok(communityService.getCommunities());
    }

    @PutMapping("/v1/community/{id}")
    public ResponseEntity<CommunityEntity> updateCommunity(@PathVariable Long id, @RequestBody CommunityEntity communityEntity) {
        CommunityEntity savedCommunityEntity = communityService.updateCommunity(id, communityEntity);

        return ResponseEntity
                .created(getCommunityUri(communityEntity))
                .body(savedCommunityEntity);
    }

    @DeleteMapping("/v1/community/{id}")
    public ResponseEntity<Object> deleteCommunity(@PathVariable Long id) {
        communityService.deleteCommunity(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/v1/community/{id}/resident")
    public ResponseEntity<Set<ResidentEntity>> getCommunityResidents(@PathVariable Long id) {
        return ResponseEntity
                .ok(communityService.getCommunityResidents(id));
    }

    private static URI getCommunityUri(CommunityEntity communityEntity) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(communityEntity.getId())
                .toUri();
    }
}
