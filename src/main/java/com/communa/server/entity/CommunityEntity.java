package com.communa.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"residentEntities"})
@Table(name = "community")
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private Set<ResidentEntity> residentEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private Set<ParkingLotEntity> parkingLots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ResidentEntity> getResidentEntities() {
        return residentEntities;
    }

    public void setResidentEntities(Set<ResidentEntity> residentEntities) {
        this.residentEntities = residentEntities;
    }

    public Set<ParkingLotEntity> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(Set<ParkingLotEntity> parkingLots) {
        this.parkingLots = parkingLots;
    }
}
