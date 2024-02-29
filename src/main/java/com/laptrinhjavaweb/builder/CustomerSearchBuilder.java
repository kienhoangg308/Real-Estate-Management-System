package com.laptrinhjavaweb.builder;

import com.laptrinhjavaweb.dto.CustomerDTO;

public class CustomerSearchBuilder {

    private String fullName;

    private String phone;

    private String email;

    private Long staffId;

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffId() {
        return staffId;
    }

    private CustomerSearchBuilder(Builder builder){
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.staffId = builder.staffId;
        this.phone = builder.phone;
    }

    public static class Builder{
        private String fullName;

        private String phone;

        private String email;

        private Long staffId;

        public Builder setStaffId(Long staffId){
            this.staffId = staffId;
            return this;
        }

        public Builder setName(String fullName){
            this.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }

        public CustomerSearchBuilder build(){
            return new CustomerSearchBuilder(this);
        }

    }
}
