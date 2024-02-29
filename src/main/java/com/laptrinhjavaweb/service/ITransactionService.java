package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerTransactionDTO;
import com.laptrinhjavaweb.dto.TransactionInputDTO;

import java.util.List;

public interface ITransactionService {

    List<CustomerTransactionDTO> getOutput(Long customerId);

    void saveTransaction(TransactionInputDTO transactionInputDTO);
}
