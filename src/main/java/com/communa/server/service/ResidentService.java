package com.communa.server.service;

import com.communa.server.exception.ResidentDuplicateException;
import com.communa.server.exception.ResidentNotFoundException;
import com.communa.server.repository.ResidentRepository;
import com.communa.server.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    public Resident registerResident(Resident resident) {
        if(residentRepository.findByEmail(resident.getEmail()).isPresent()){
            throw new ResidentDuplicateException(format("Resident with email %s already exist", resident.getEmail()));
        }

        return residentRepository.save(resident);
    }

    public Resident updateResident(Long id, Resident resident) {
        if(!residentRepository.existsById(id)) {
            throw new ResidentNotFoundException(String.format("Resident with id %d not found", id));
        }
        resident.setId(id);

        return residentRepository.save(resident);
    }

    public Resident getResident(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new ResidentNotFoundException(String.format("Resident with id %d not found", id)));
    }

    public Set<Resident> getResidents() {
        HashSet<Resident> residents = new HashSet<>();
        residentRepository.findAll().forEach(residents::add);

        return residents;
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
    }
}
