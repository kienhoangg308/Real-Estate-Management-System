package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByCustomerId(Long customerId);

    //void save(TransactionEntity transactionEntity);
    //void saveAll(List<TransactionEntity> transactionEntities);
}
