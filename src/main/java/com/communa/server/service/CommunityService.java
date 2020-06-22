package com.communa.server.service;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ParkingLotEntity;
import com.communa.server.entity.ResidentEntity;
import com.communa.server.exception.DuplicateException;
import com.communa.server.exception.NotFoundException;
import com.communa.server.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class CommunityService {
    
    @Autowired
    private CommunityRepository communityRepository;

    public CommunityEntity createCommunity(CommunityEntity communityEntity) {
        if(communityRepository.findByName(communityEntity.getName()).isPresent()){
            throw new DuplicateException(format("Community with Name %s already exist", communityEntity.getName()));
        }

        return communityRepository.save(communityEntity);
    }

    public CommunityEntity updateCommunity(Long id, CommunityEntity communityEntity) {
        if(!communityRepository.existsById(id)) {
            throw new NotFoundException(format("Community with name %s not found", id));
        }
        communityEntity.setId(id);

        return communityRepository.save(communityEntity);
    }

    public CommunityEntity getCommunity(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Community with id %d not found", id)));
    }

    public Set<CommunityEntity> getCommunities() {
        HashSet<CommunityEntity> communities = new HashSet<>();
        communityRepository.findAll().forEach(communities::add);

        return communities;
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    public Set<ResidentEntity> getCommunityResidents(Long id) {
        CommunityEntity community = communityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Community with id %d not found", id)));

        return community.getResidents();
    }

    public Set<ParkingLotEntity> getCommunityParkingLots(Long id) {
        CommunityEntity community = communityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Community with id %d not found", id)));

        return community.getParkingLots();
    }
}
