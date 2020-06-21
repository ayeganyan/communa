package com.communa.server.entity;

import com.communa.server.validation.Age;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "resident")
public class ResidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @Size(min=6)
    @NotNull
    @Column(name = "password")
    private String password;

    @Size(min=2)
    @NotNull
    @Column(name = "name")
    private String name;

    @Size(min=2)
    @NotNull
    @Column(name = "surname")
    private String surname;

    @Past
    @NotNull
    @Age(from = 18, to = 35)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthdate")
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_community")
    private CommunityEntity community;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_parkinglot")
    private ParkingLotEntity parkingLot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public CommunityEntity getCommunity() {
        return community;
    }

    public void setCommunity(CommunityEntity community) {
        this.community = community;
    }

    public ParkingLotEntity getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLotEntity parkingLot) {
        this.parkingLot = parkingLot;
    }
}
