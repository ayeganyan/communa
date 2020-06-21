package com.communa.server.service;

import com.communa.server.exception.DuplicateException;
import com.communa.server.exception.NotFoundException;
import com.communa.server.repository.CommunityRepository;
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

    public ResidentEntity registerResident(ResidentEntity residentEntity) {
        if(residentRepository.findByEmail(residentEntity.getEmail()).isPresent()){
            throw new DuplicateException(format("Resident with email %s already exist", residentEntity.getEmail()));
        }

        return residentRepository.save(residentEntity);
    }

    public ResidentEntity updateResident(Long id, ResidentEntity residentEntity) {
        if(!residentRepository.existsById(id)) {
            throw new NotFoundException(String.format("Resident with id %d not found", id));
        }
        residentEntity.setId(id);

        return residentRepository.save(residentEntity);
    }

    public ResidentEntity getResident(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Resident with id %d not found", id)));
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

        if(!communityRepository.findById(communityId).isPresent()) {
            throw new NotFoundException("Community with id %d not found");
        }
        if(!residentRepository.findById(residentId).isPresent()) {
            throw new NotFoundException("Resident with id %d not found");
        }
        residentRepository.updateCommunity(residentId, communityId);

        return residentRepository.findById(residentId).get();
    }

    public void leaveCommunity(Long residentId) {
        residentRepository.leaveCommunity(residentId);
    }
}
