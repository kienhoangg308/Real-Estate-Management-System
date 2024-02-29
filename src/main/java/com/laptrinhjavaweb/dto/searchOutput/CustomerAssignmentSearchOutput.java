package com.laptrinhjavaweb.dto.searchOutput;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class CustomerAssignmentSearchOutput {

    private String fullName;
    private Long staffId;
    private String checked;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}