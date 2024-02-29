package com.laptrinhjavaweb.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;

    @Column(name = "district")
    private String district;

    private String type;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    //apply cascade vao
    private List<RentAreaEntity> rentAreas = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    //manytomany khong can khai bao cascade
    @JoinTable(name = "buildingassignment",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "userid", nullable = false))
    private List<UserEntity> user = new ArrayList<>();


    public BuildingEntity(){

    }

    public BuildingEntity(Long id) {
        super();
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "structure")
    private String structure;

    @Column(name = "floorarea")
    private Integer floorArea;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "rentprice")
    private Integer rentPrice;


    @Column(name="rentareadescription")
    private String rentAreaDescription;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "renttime")
    private Integer rentTime;

    @Column(name = "decorationtime")
    private Integer decorationTime;

    @Column(name = "servicefee")
    private Integer serviceFee;

    @Column(name = "motorbikefee")
    private Integer motorbikeFee;

    @Column(name = "overtimefee")
    private Integer overtimeFee;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "waterfee")
    private Integer waterFee;

    @Column(name = "carcost")
    private Integer carCost;

    @Column(name="electricbill")
    private Integer electricBill;


    @Column(name = "brokeragefee")
    private Float brokerageFee;

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkOfBuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "image")
    private String image;

    @Column(name="managerphone")
    private String managerPhone;

    @Column(name="managername")
    private String managerName;

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getElectricBill() {
        return electricBill;
    }

    public void setElectricBill(Integer electricBill) {
        this.electricBill = electricBill;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<UserEntity> getUser() {
        return user;
    }

    public void setUser(List<UserEntity> user) {
        this.user = user;
    }

    public String getRentAreaDescription() {
        return rentAreaDescription;
    }

    public void setRentAreaDescription(String rentAreaDescription) {
        this.rentAreaDescription = rentAreaDescription;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public Integer getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(Integer decorationTime) {
        this.decorationTime = decorationTime;
    }

    public Float getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Float brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkOfBuilding() {
        return linkOfBuilding;
    }

    public void setLinkOfBuilding(String linkOfBuilding) {
        this.linkOfBuilding = linkOfBuilding;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getMotorbikeFee() {
        return motorbikeFee;
    }

    public void setMotorbikeFee(Integer motorbikeFee) {
        this.motorbikeFee = motorbikeFee;
    }

    public Integer getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Integer overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(Integer waterFee) {
        this.waterFee = waterFee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBasement() {
        return this.numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
}
