package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name="rentarea")
public class RentAreaEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @ManyToOne
    @JoinColumn(name="buildingid")
    private BuildingEntity building;

    public RentAreaEntity(){

    }

    public RentAreaEntity(Integer value, BuildingEntity building) {
        this.value = value;
        this.building = building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
