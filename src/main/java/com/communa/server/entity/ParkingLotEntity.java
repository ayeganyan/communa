package com.communa.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parkinglot")
public class ParkingLotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "fk_resident")
    private ResidentEntity acquiredBy;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fk_community")
    private CommunityEntity community;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
