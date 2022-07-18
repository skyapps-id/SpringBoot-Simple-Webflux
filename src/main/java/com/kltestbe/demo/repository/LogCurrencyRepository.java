package com.kltestbe.demo.repository;

import org.springframework.stereotype.Repository;

import com.kltestbe.demo.model.LogCurrency;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LogCurrencyRepository extends CrudRepository<LogCurrency, Integer> {
}
