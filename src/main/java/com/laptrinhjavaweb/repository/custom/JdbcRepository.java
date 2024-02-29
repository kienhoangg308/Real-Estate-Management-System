package com.laptrinhjavaweb.repository.custom;

public interface JdbcRepository<T> {
    T findById(Long id);
}
