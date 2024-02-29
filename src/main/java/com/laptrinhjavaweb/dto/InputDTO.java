package com.laptrinhjavaweb.dto;

import java.util.List;

public class InputDTO {
    private List<Long> buildingIds;

    private List<Long> customerIds;

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public List<Long> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List<Long> buildingIds) {
        this.buildingIds = buildingIds;
    }
}
