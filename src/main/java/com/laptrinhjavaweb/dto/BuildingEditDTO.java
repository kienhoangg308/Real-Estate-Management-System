package com.laptrinhjavaweb.dto;

public class BuildingEditDTO {

    private Long id;
    private String name;

    private String street;

    private String ward;

    private String[] type;

    private String district;

    private String structure;

    private Integer numberOfBasement;

    private String floorArea;

    private String direction;

    private String level;

    private String rentArea;

    private String rentAreaDescription;

    private String rentPriceDescription;

    private Integer serviceFee;

    private Integer carCost;

    private Integer motorbikeFee;

    private Integer overtimeFee;

    private Integer electricBill;

    private Integer deposit;

    private String payment;

    private Integer rentTime;

    private Integer decorationTime;

    private String managerName;

    private String managerPhone;

    private Integer brokerageFee;

    private String image;

    private String imageBase64;

    private String imageName;

    private Integer rentPrice;

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return image;
    }

    public String getImageName() {
        return imageName;
    }

    public String getRentAreaDescription() {
        return rentAreaDescription;
    }

    public void setRentAreaDescription(String rentAreaDescription) {
        this.rentAreaDescription = rentAreaDescription;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
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

    public Integer getElectricBill() {
        return electricBill;
    }

    public void setElectricBill(Integer electricBill) {
        this.electricBill = electricBill;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Integer getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Integer brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
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

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
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

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
