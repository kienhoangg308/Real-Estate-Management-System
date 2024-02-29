package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder, Pageable pageable);

    List<CustomerEntity> search(CustomerSearchBuilder customerSearchBuilder);
}
