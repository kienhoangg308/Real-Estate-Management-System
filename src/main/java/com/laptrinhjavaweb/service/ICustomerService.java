package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.*;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.CustomerSearchOutput;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    List<CustomerSearchOutput> findCustomer(CustomerDTO customerDTO, Pageable pageable);

    List<CustomerSearchOutput> search(CustomerDTO customerDTO);

    AssignmentOutputModel assignBuildingToStaffs(AssignmentInputModel assignmentInputModel);

    ResponseDTO loadStaff(Long customerId);

    CustomerEditDTO saveCustomer(CustomerEditDTO customerEditDTO);

    CustomerEditDTO findById(Long id);

    void deleteCustomers(List<Long> ids);

}

