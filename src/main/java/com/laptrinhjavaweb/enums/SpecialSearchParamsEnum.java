package com.laptrinhjavaweb.enums;

public enum SpecialSearchParamsEnum {
    DISTRICT("district"),
    STAFF_ID("staffId"),
    RENT_PRICE_FROM("rentPriceFrom"),
    RENT_PRICE_TO("rentPriceFrom"),
    RENT_AREA_FROM("rentAreaFrom"),
    RENT_AREA_TO("rentAreaTo");

    private final String value;

    SpecialSearchParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
