package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.AssignmentInputModel;
import com.laptrinhjavaweb.dto.AssignmentOutputModel;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.CustomerEditDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.CustomerAssignmentSearchOutput;
import com.laptrinhjavaweb.dto.searchOutput.CustomerSearchOutput;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter converter;

    @Autowired
    private TransactionRepository transactionRepository;

    private List<Long> findItemOfSourceNotTarget(List<Long> source, List<Long> target) {
        List<Long> itemOfSourceButNotTarget = new ArrayList<>();
        for(int i =0;i<source.size();i++) {
            if(!target.contains(source.get(i))) {
                itemOfSourceButNotTarget.add(source.get(i));
            }
        }
        return itemOfSourceButNotTarget;
    }

    private CustomerSearchBuilder convertToCustomerSearchBuilder(CustomerDTO customerDTO){
        CustomerSearchBuilder result = new CustomerSearchBuilder.Builder()
                .setName(customerDTO.getFullName())
                .setEmail(customerDTO.getEmail())
                .setPhone(customerDTO.getPhone())
                .setStaffId(customerDTO.getStaffId())
                .build();
        return result;
    }

    @Override
    public List<CustomerSearchOutput> search(CustomerDTO customerDTO) {
        List<CustomerSearchOutput> results = new ArrayList<>();
        CustomerSearchBuilder customerSearchBuilder = convertToCustomerSearchBuilder(customerDTO);
        List<CustomerEntity> customerEntities = customerRepository.search(customerSearchBuilder);

        for(CustomerEntity item: customerEntities){
            List<Long> userIds = item.getUser().stream()
                    .map(UserEntity::getId)
                    .collect(Collectors.toList());
            List<UserEntity> users = userRepository.findByIdIn(userIds);
            CustomerSearchOutput customerSearchOutput = converter.convertToCustomerSearchOutput(item, users);
            results.add(customerSearchOutput);
        }
        return results;
    }

    @Override
    public List<CustomerSearchOutput> findCustomer(CustomerDTO customerDTO, Pageable pageable) {
        List<CustomerSearchOutput> results = new ArrayList<>();
        CustomerSearchBuilder customerSearchBuilder = convertToCustomerSearchBuilder(customerDTO);
        List<CustomerEntity> customerEntities = customerRepository.searchCustomer(customerSearchBuilder,pageable);

        for(CustomerEntity item: customerEntities){
            List<Long> userIds = item.getUser().stream()
                    .map(UserEntity::getId)
                    .collect(Collectors.toList());
            List<UserEntity> users = userRepository.findByIdIn(userIds);
            CustomerSearchOutput customerSearchOutput = converter.convertToCustomerSearchOutput(item, users);
            results.add(customerSearchOutput);
        }
        return results;
    }

    @Override
    public AssignmentOutputModel assignBuildingToStaffs(AssignmentInputModel assignmentInputModel) {
        Long customerId = assignmentInputModel.getCustomerId();
        List<Long> inputIds = assignmentInputModel.getStaffIds();

        CustomerEntity customer = customerRepository.findById(customerId);
        List<UserEntity> newUsers = userRepository.findByIdIn(inputIds);
        customer.setUser(newUsers);
        customerRepository.save(customer);

        String currentIdslistString = inputIds.toString();
        currentIdslistString = currentIdslistString.substring(1, currentIdslistString.length()-1);
        AssignmentOutputModel assignmentOutputModel = new AssignmentOutputModel();
        assignmentOutputModel.setStaffIds(currentIdslistString);
        return assignmentOutputModel;
    }

    @Override
    public ResponseDTO loadStaff(Long customerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CustomerAssignmentSearchOutput> customerAssignmentSearchOutputs = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        CustomerEntity customerEntity = customerRepository.findById(customerId);

        List<Long> userIds = userEntities.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        List<UserEntity> users = customerEntity.getUser();
        List<Long> assignedUserIds = users.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        List<Long> uncheckedUserIds = findItemOfSourceNotTarget(userIds, assignedUserIds);

        customerAssignmentSearchOutputs = userEntities.stream()
                .map(userEntity -> {
                    CustomerAssignmentSearchOutput customerAssignmentSearchOutput = new CustomerAssignmentSearchOutput();
                    customerAssignmentSearchOutput.setFullName(userEntity.getFullName());
                    customerAssignmentSearchOutput.setStaffId(userEntity.getId());
                    if(assignedUserIds.contains(userEntity.getId())){
                        customerAssignmentSearchOutput.setChecked("checked");
                    }else if (uncheckedUserIds.contains(userEntity.getId())) {
                        customerAssignmentSearchOutput.setChecked("unchecked");
                    }
                    return customerAssignmentSearchOutput;
                })
                .collect(Collectors.toList());

        responseDTO.setMessage("success");
        responseDTO.setData(customerAssignmentSearchOutputs);

        return responseDTO;
    }


    @Override
    @Transactional
    public CustomerEditDTO saveCustomer(CustomerEditDTO customerEditDTO) {
        CustomerEntity customer = converter.convertToCustomerEntity(customerEditDTO);
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        if(customerEditDTO.getId() != null){
            CustomerEntity customerEntity = customerRepository.findById(customerEditDTO.getId());
            transactionEntities = transactionRepository.findByCustomerId(customerEditDTO.getId());
            customer.setUser(customerEntity.getUser());

            for(TransactionEntity transactionEntity : transactionEntities) {
                transactionEntity.setCustomer(customer);
            }
        }

        customer.setTransactions(transactionEntities);
        return converter.convertToCustomerEditDTO(customerRepository.save(customer));
    }


    @Override
    public CustomerEditDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        CustomerEditDTO customerEditDTO = converter.convertToCustomerEditDTO(customerEntity);
        return customerEditDTO;
    }

    @Override
    @Transactional
    public void deleteCustomers(List<Long> ids) {
        List<CustomerEntity> customers = customerRepository.findByIdIn(ids);
        if(customers.size() == ids.size()){
            customerRepository.deleteByIdIn(ids);
        }
    }


}
