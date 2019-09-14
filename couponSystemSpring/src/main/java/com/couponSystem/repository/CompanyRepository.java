package com.couponSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponSystem.javabeans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompName(String companyName);

	Company findById(long id);

	// @Query("SELECT * FROM COMPANY WHERE compName=? AND password=?")
	Company findByCompNameAndPassword(String compName, String password);
}
