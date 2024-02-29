package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerEditDTO;
import com.laptrinhjavaweb.dto.searchOutput.CustomerSearchOutput;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    private String convertAssignedStaff(List<UserEntity> users){
        List<String> userNames = users.stream()
                .map(UserEntity::getFullName)
                .collect(Collectors.toList());
        return String.join(",", userNames);
    }

    public CustomerSearchOutput convertToCustomerSearchOutput(CustomerEntity customerEntity, List<UserEntity> users){
        CustomerSearchOutput customerSearchOutput = modelMapper.map(customerEntity, CustomerSearchOutput.class);
        customerSearchOutput.setStaffName(convertAssignedStaff(users));
        return customerSearchOutput;
    }

    public CustomerEntity convertToCustomerEntity (CustomerEditDTO customerEditDTO){
        CustomerEntity customerEntity = modelMapper.map(customerEditDTO, CustomerEntity.class);
        return customerEntity;
    }

    public CustomerEditDTO convertToCustomerEditDTO(CustomerEntity customerEntity) {
        CustomerEditDTO customerEditDTO = modelMapper.map(customerEntity, CustomerEditDTO.class);
        return customerEditDTO;
    }

}
