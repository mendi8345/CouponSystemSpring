package com.couponSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponSystem.javabeans.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
