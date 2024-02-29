package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>, CustomerRepositoryCustom {

    CustomerEntity findById(Long id);

    List<CustomerEntity> findByIdIn(List<Long> ids);

    void deleteByIdIn(List<Long> ids);
}
