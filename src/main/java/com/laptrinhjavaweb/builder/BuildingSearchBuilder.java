package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
    private String name;
    private String district;
    private Integer floorArea;
    private String street;
    private Long staffId;
    private String ward;
    private Integer numberOfBasement;
	private String[] type;
    private Integer rentPriceTo;
    private Integer rentPriceFrom;
    private Integer rentAreaFrom;
    private Integer rentAreaTo;
    private String direction;
    private String level;
    private String managerName;
    private String managerPhone;

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public String[] getType() {
        return type;
    }

    public Integer getRentPriceTo() {
        return rentPriceTo;
    }

    public Integer getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Integer getRentAreaTo() {
        return rentAreaTo;
    }

    public Integer getRentAreaFrom() {
        return rentAreaFrom;
    }

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
        this.floorArea = builder.floorArea;
        this.numberOfBasement = builder.numberOfBasement;
        this.street = builder.street;
        this.ward = builder.ward;
		this.type = builder.type;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
        this.level = builder.level;
        this.direction = builder.direction;
		this.staffId = builder.staffId;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
    }

    public static class Builder {

        private String name;
        private String district;
        private String street;
        private String ward;
        private Integer floorArea;
        private Integer numberOfBasement;
		private String[] type = new String[] {};
        private Integer rentPriceFrom;
        private Integer rentPriceTo;
        private Integer rentAreaFrom;
        private Integer rentAreaTo;
        private String level;
        private String direction;
        private Long staffId;
        private String managerName;
        private String managerPhone;

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setFloorArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setType(String[] type) {
			this.type = type;
			return this;
		}

        public Builder setRentPriceFrom(Integer rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Integer rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setRentAreaFrom(Integer rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Integer rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
