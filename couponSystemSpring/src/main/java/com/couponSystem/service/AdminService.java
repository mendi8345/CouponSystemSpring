package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;

public interface AdminService {

	public void createCompany(Company company) throws Exception;

	public void removeCompany(long id) throws Exception;

	public void updateCompany(Company company) throws Exception;

	public Company getCompany(long id) throws Exception;

	public List<Company> getAllCompany() throws Exception;

	public void insertCustomer(Customer customer) throws Exception;

	public void removeCustomer(long id) throws Exception;

	public void updateCustomer(Customer customer) throws Exception;

	public Customer getCustomer(long id) throws Exception;

	public List<Customer> getAllCustomer() throws Exception;

	// @Override
	// public CouponClientFacade login(String name, String password, ClientType
	// clientType) {
	//
	// return null;
	//
	// }
}
