package com.laptrinhjavaweb.dto;

import java.util.List;

public class CustomerTransactionDTO {

    private String name;

    private String code;

    private List<TransactionDTO> transaction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TransactionDTO> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionDTO> transaction) {
        this.transaction = transaction;
    }
}
