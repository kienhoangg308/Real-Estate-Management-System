package com.laptrinhjavaweb.dto.searchOutput;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class BuildingSearchOutput extends AbstractDTO {

    //private Long id;
    private String name;

    private Integer floorArea;
    private String address;
    private String type;
    private String structure;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private String rentAreas;
    private String rentPriceDescription;
    private String district;
    private String staffs;
    private String rentPrice;

    private String rentArea;

    public BuildingSearchOutput(){


    }

    public BuildingSearchOutput(Long id) {
        super(id);
        //this.id = id;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStaffs() {
        return staffs;
    }

    public void setStaffs(String staffs) {
        this.staffs = staffs;
    }

    public String getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(String rentAreas) {
        this.rentAreas = rentAreas;
    }
}
