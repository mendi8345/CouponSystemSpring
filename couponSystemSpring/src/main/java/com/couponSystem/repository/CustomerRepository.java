package com.couponSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponSystem.javabeans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustName(String custName);

	Customer findById(long id);

	Customer findByCustNameAndPassword(String name, String password);

	boolean existsById(long id);
}
