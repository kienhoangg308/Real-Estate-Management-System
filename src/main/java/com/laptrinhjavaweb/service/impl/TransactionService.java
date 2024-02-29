package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.CustomerEditDTO;
import com.laptrinhjavaweb.dto.CustomerTransactionDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.TransactionInputDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.TransactionEnum;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerTransactionDTO> getOutput(Long customerId) {
        List<CustomerTransactionDTO> results = new ArrayList<>();
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerId(customerId);
        List<TransactionEnum> transactionEnums = Arrays.asList(TransactionEnum.class.getEnumConstants());
        SimpleDateFormat formatter = new SimpleDateFormat("   dd-MM-yyyy");
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for(TransactionEnum transactionEnum: transactionEnums){
            CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
            customerTransactionDTO.setCode(transactionEnum.name());
            customerTransactionDTO.setName(transactionEnum.getValue());
            List<TransactionDTO> transactionDTOS = new ArrayList<>();
            for(TransactionEntity transactionEntity: transactionEntities){
                if(transactionEntity.getCode().equals(transactionEnum.name())){
                    TransactionDTO transactionDTO = new TransactionDTO();
                    String strDate = formatter.format(transactionEntity.getCreatedDate());
                    transactionDTO.setDate(strDate);
                    //transactionDTO.setDate("12-1-2023");
                    transactionDTO.setNote(transactionEntity.getNote());
                    transactionDTOS.add(transactionDTO);
                }
            }
            customerTransactionDTO.setTransaction(transactionDTOS);
            results.add(customerTransactionDTO);
        }
        return results;
    }

    @Override
    public void saveTransaction(TransactionInputDTO transactionInputDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        CustomerEntity customer = new CustomerEntity();
        transactionEntity.setCode(transactionInputDTO.getCode());
        transactionEntity.setNote(transactionInputDTO.getNote());
        customer.setId(transactionInputDTO.getCustomerId());
        transactionEntity.setCustomer(customer);
        transactionRepository.save(transactionEntity);
    }
}
