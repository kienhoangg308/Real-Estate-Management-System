package com.laptrinhjavaweb.enums;

public enum DistrictEnum {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_PHU_NHUAN("Quận Phú Nhuận"),
    QUAN_BINH_THANH("Quận Bình Thạnh"),
    QUAN_TAN_PHU("Quận Tân Phú"),
    QUAN_GO_VAP("Quận Gò Vấp"),
    QUAN_THU_DUC("Quận Thủ Đức");


    private final String value;

    DistrictEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DistrictEnum fromValue(String value){
        for(DistrictEnum districtEnum : DistrictEnum.values()){
            if(districtEnum.getValue() == value ){
                return districtEnum;
            }
        }
        throw new IllegalArgumentException("No value");
    }
}

