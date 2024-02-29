package com.laptrinhjavaweb.enums;

public enum BuildingTypesEnum {

    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất");

    private final String value;

    BuildingTypesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BuildingTypesEnum fromValue(String value){
        for(BuildingTypesEnum buildingTypesEnum: BuildingTypesEnum.values()){
            if(buildingTypesEnum.getValue() == value){
                return buildingTypesEnum;
            }
        }
        throw new IllegalArgumentException("No value");
    }
}
