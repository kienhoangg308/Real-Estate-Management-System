package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.*;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.CustomerSearchOutput;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("customerAPIOfAdmin")
@RequestMapping({"/api/customer"})
@Slf4j
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public CustomerEditDTO saveCustomer(@RequestBody CustomerEditDTO customerEditDTO){
        CustomerEditDTO result = customerService.saveCustomer(customerEditDTO);
        return result;
    }

    @GetMapping
    public List<CustomerSearchOutput> findCustomer(@RequestBody CustomerDTO customerDTO){
        List<CustomerSearchOutput> results = customerService.search(customerDTO);
        return results;
    }

    @GetMapping({"/{customerid}/staffs"})
    public ResponseDTO loadStaff(@PathVariable("customerid") Long customerId) {
        ResponseDTO result = customerService.loadStaff(customerId);
        return result;
    }

    @GetMapping({"/{customerid}/transactions"})
    public List<CustomerTransactionDTO> getTransactions(@PathVariable("customerid") Long customerId) {
        List<CustomerTransactionDTO> results = transactionService.getOutput(customerId);
        return results;
    }

    @GetMapping("/{customerid}")
    public CustomerEditDTO findById(@PathVariable("customerid") Long id){
        CustomerEditDTO customerEditDTO = customerService.findById(id);
        return customerEditDTO;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionInputDTO transactionInputDTO){
        transactionService.saveTransaction(transactionInputDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignment")
    public AssignmentOutputModel assignCustomerToStaffs(@RequestBody AssignmentInputModel input) {
        AssignmentOutputModel output = customerService.assignBuildingToStaffs(input);
        return output;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomers(@RequestBody InputDTO inputDTO){
        customerService.deleteCustomers(inputDTO.getCustomerIds());
        return ResponseEntity.noContent().build();
    }
}
